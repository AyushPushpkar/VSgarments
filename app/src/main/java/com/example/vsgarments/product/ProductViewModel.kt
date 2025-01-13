package com.example.vsgarments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Resource
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

    private val _productState = MutableStateFlow<Resource<List<ProductItem>>>(Resource.Unspecified())
    val productState: StateFlow<Resource<List<ProductItem>>> = _productState

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                val products = repository.getProducts()
                _productState.value = Resource.Success(products)
            } catch (e: Exception) {
                _productState.value = Resource.Error("Failed to load products: ${e.message}")
            }
        }
    }

    fun addProduct(product: ProductItem) {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                repository.addProduct(product)
                loadProducts() // Refresh the list
            } catch (e: Exception) {
                _productState.value = Resource.Error("Failed to add product: ${e.message}")
            }
        }
    }

    fun updateProduct(product: ProductItem) {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                repository.updateProductById(product.id, product)
                loadProducts() // Refresh the list
            } catch (e: Exception) {
                _productState.value = Resource.Error("Failed to update product: ${e.message}")
            }
        }
    }

    fun deleteProduct(productId: String) {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                repository.deleteProductById(productId)
                loadProducts() // Refresh the list
            } catch (e: Exception) {
                _productState.value = Resource.Error("Failed to delete product: ${e.message}")
            }
        }
    }
}
