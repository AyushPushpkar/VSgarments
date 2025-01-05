package com.example.vsgarments.product

import com.example.vsgarments.dataStates.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseProductRepository @Inject constructor(
    private val db: FirebaseFirestore
) : ProductRepository {

    private val productsCollection = db.collection("products")

    override suspend fun addProduct(product: ProductItem): String {
        val docRef = productsCollection.document(product.id) // Use product.id as the document ID
        docRef.set(product).await()
        return docRef.id
    }

    override suspend fun getProducts(): List<ProductItem> {
        val snapshot = productsCollection.get().await()
        return snapshot.documents.mapNotNull { it.toObject(ProductItem::class.java) }
    }

    override suspend fun updateProductById(id: String, updatedProduct: ProductItem) {
        val docRef = productsCollection.document(id)
        docRef.set(updatedProduct).await()
    }

    override suspend fun deleteProductById(id: String) {
        val docRef = productsCollection.document(id)
        docRef.delete().await()
    }
}
