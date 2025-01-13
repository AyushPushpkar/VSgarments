package com.example.vsgarments.product

import com.example.vsgarments.dataStates.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.appwrite.models.InputFile
import io.appwrite.services.Storage
import kotlinx.coroutines.tasks.await
import java.nio.file.Paths
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseProductRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val appWriteStorage: Storage
) : ProductRepository {

    private val productsCollection = db.collection("products")

    override suspend fun addProduct(product: ProductItem): String {

        // Ensure the product has an image URI before proceeding
        val imageUri = product.localImageUri ?: throw IllegalArgumentException("Product image URI is required")

        val path = Paths.get(imageUri.path)

        // Upload the image to Firebase Storage
        val file = appWriteStorage.createFile(
            bucketId = "6783988f000b21f9f0da",
            fileId = UUID.randomUUID().toString(),
            file = InputFile.fromPath(path.toString())
        )

        // Construct the file URL for the uploaded image
        val imageUrl = "https://cloud.appwrite.io/v1/storage/buckets/6783988f000b21f9f0da/files/${file.id}/view"

        // Update the product with the image URL
        val productWithImageUrl = product.copy(remoteImageUrl = imageUrl, localImageUri = null)

        // Save the product in Firestore
        val docRef = productsCollection.document(productWithImageUrl.id)
        docRef.set(productWithImageUrl).await()

        return docRef.id

    }

    override suspend fun getProducts(): List<ProductItem> {
        val snapshot = productsCollection.get().await()
        return snapshot.documents.mapNotNull { it.toObject(ProductItem::class.java) }
    }

    override suspend fun updateProductById(id: String, updatedProduct: ProductItem) {
        val docRef = productsCollection.document(id)

        if (updatedProduct.localImageUri != null) {

            // Check if the product has a previous image and delete it
            updatedProduct.remoteImageUrl?.let { previousImageUrl ->

                val fileId = extractFileIdFromUrl(previousImageUrl)
                appWriteStorage.deleteFile(bucketId = "6783988f000b21f9f0da", fileId = fileId)
            }

            val path = Paths.get(updatedProduct.localImageUri.path)

            try {
                val file = appWriteStorage.createFile(
                    bucketId = "6783988f000b21f9f0da",
                    fileId = UUID.randomUUID().toString(),
                    file = InputFile.fromPath(path.toString())
                )

                // Construct the file URL for the new image
                val newImageUrl = "https://cloud.appwrite.io/v1/storage/buckets/6783988f000b21f9f0da/files/${file.id}/view"

                // Update the product with the new image URL and set localImageUri to null
                val updatedProductWithNewImage = updatedProduct.copy(
                    remoteImageUrl = newImageUrl,  // Now using the URL as a String
                    localImageUri = null
                )

                // Save the updated product in Firestore
                docRef.set(updatedProductWithNewImage).await()

            } catch (e: Exception) {
                // Handle error during file upload (optional)
                println("Error uploading new image: ${e.message}")
            }

        } else {
            // Update product without changing the image
            docRef.set(updatedProduct).await()
        }
    }

    override suspend fun deleteProductById(id: String) {
        val docRef = productsCollection.document(id)

        val product = docRef.get().await().toObject(ProductItem::class.java)
        product?.remoteImageUrl?.let { imageUrl ->
            val fileId = extractFileIdFromUrl(imageUrl)
            appWriteStorage.deleteFile(bucketId = "6783988f000b21f9f0da", fileId = fileId)
        }

        docRef.delete().await()
    }

    private fun extractFileIdFromUrl(url: String): String {
        // Adjust this logic based on your Appwrite URL structure
        return url.substringAfterLast("/")
    }
}
