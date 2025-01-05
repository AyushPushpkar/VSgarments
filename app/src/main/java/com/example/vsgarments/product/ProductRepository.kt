package com.example.vsgarments.product

import com.example.vsgarments.dataStates.ProductItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface ProductRepository {
    suspend fun addProduct(product: ProductItem): String
    suspend fun getProducts(): List<ProductItem>
    suspend fun updateProductById(id: String, updatedProduct: ProductItem)
    suspend fun deleteProductById(id: String)
}

