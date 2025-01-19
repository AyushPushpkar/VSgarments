package com.example.vsgarments.product

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.vsgarments.dataStates.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.appwrite.Permission
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

    override suspend fun addProduct(product: ProductItem , context: Context): String {

        // Ensure the product has an image URI before proceeding
        val imageUri = product.localImageUri ?: throw IllegalArgumentException("Product image URI is required")

        // Log the URI for debugging
        Log.d("ProductScreen", "Image URI: $imageUri")

        // Resolve the content URI to a file path (if it's a content URI, like from the gallery or file picker)
        val path = getRealPathFromURI(imageUri , context)
            ?: throw IllegalArgumentException("Failed to resolve image URI to a file path")

        try {
            // Upload the image to Appwrite Storage
            val file = appWriteStorage.createFile(
                bucketId = "6783988f000b21f9f0da",
                fileId = product.id,
                file = InputFile.fromPath(path),
                permissions = listOf(
                    Permission.read("any")
                )
            )
            println(file.permissions)

            // Construct the file URL for the uploaded image
            val imageUrl = "https://cloud.appwrite.io/v1/storage/buckets/${file.bucketId}/files/${file.id}/view?project=67838c5c0028311de936&project=67838c5c0028311de936&mode=admin"

            // Update the product with the image URL
            val productWithImageUrl = product.copy(remoteImageUrl = imageUrl, localImageUri = null)

            // Save the product in Firestore
            val docRef = productsCollection.document(productWithImageUrl.id)
            docRef.set(productWithImageUrl).await()

            return docRef.id

        } catch (e: Exception) {
            Log.e(
                "ProductUpload",
                "Error uploading product image: ${e.message}"
            )
            throw e
        }

    }

    override suspend fun getProducts(): List<ProductItem> {
        val snapshot = productsCollection.get().await()
        return snapshot.documents.mapNotNull { it.toObject(ProductItem::class.java) }
    }

    override suspend fun updateProductById(id: String, updatedProduct: ProductItem , context: Context) {

        Log.d("UpdateProduct", "Updating product with ID: $id")

        val docRef = productsCollection.document(id)

        if (updatedProduct.localImageUri != null) {

            val product = docRef.get().await().toObject(ProductItem::class.java)
            product?.remoteImageUrl?.let {
                val fileId = product.id
                appWriteStorage.deleteFile(bucketId = "6783988f000b21f9f0da", fileId = fileId)
            }

            val imageUri = updatedProduct.localImageUri ?: throw IllegalArgumentException("Product image URI is required")

            val path = getRealPathFromURI(imageUri , context)
                ?: throw IllegalArgumentException("Failed to resolve image URI to a file path")

            try {
                val file = appWriteStorage.createFile(
                    bucketId = "6783988f000b21f9f0da",
                    fileId = updatedProduct.id,
                    file = InputFile.fromPath(path.toString())
                )

                // Construct the file URL for the new image
                val newImageUrl = "https://cloud.appwrite.io/v1/storage/buckets/6783988f000b21f9f0da/files/${file.id}/view?project=67838c5c0028311de936&project=67838c5c0028311de936&mode=admin"

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
            try {
                docRef.set(updatedProduct).await()
                Log.d("UpdateProduct", "Firestore document updated successfully.")
            } catch (e: Exception) {
                Log.e("UpdateProduct", "Failed to update Firestore document: ${e.message}")
            }
        }
    }

    override suspend fun deleteProductById(id: String) {

        val docRef = productsCollection.document(id)

        val product = docRef.get().await().toObject(ProductItem::class.java)
        product?.remoteImageUrl?.let {
            val fileId = product.id
            appWriteStorage.deleteFile(bucketId = "6783988f000b21f9f0da", fileId = fileId)
        }

        docRef.delete().await()
    }

    override suspend fun getProductById(productId: String) : ProductItem? {
        val docRef = productsCollection.document(productId)
        val snapshot = docRef.get().await()
        return snapshot.toObject(ProductItem::class.java)
    }

    private fun extractFileIdFromUrl(url: String): String {
        // Adjust this logic based on your Appwrite URL structure
        return url.substringAfterLast("/")
    }
}

fun getRealPathFromURI(contentUri: Uri , context: Context): String? {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(contentUri, proj, null, null, null)
    cursor?.moveToFirst()
    val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    val path = cursor?.getString(columnIndex ?: -1)
    cursor?.close()
    return path
}
