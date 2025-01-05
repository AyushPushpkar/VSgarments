package com.example.vsgarments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.dataStates.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList: StateFlow<List<ProductItem>> = _productList

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val products = repository.getProducts()
            _productList.value = products
        }
    }

    fun addProduct(product: ProductItem) {
        viewModelScope.launch {
            repository.addProduct(product)
            loadProducts() // Refresh the list
        }
    }

    fun updateProduct(product: ProductItem) {
        viewModelScope.launch {
            repository.updateProductById(product.id, product)
            loadProducts() // Refresh the list
        }
    }

    fun deleteProduct(productId: String) {
        viewModelScope.launch {
            repository.deleteProductById(productId)
            loadProducts() // Refresh the list
        }
    }
}
