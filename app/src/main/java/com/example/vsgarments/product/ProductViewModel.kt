package com.example.vsgarments.product

import android.content.Context
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

    fun addProduct(product: ProductItem , context: Context) {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                repository.addProduct(product, context)
                loadProducts() // Refresh the list
            } catch (e: Exception) {
                _productState.value = Resource.Error("Failed to add product: ${e.message}")
            }
        }
    }

    fun updateProduct(productId: String , product: ProductItem) {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                repository.updateProductById(productId, product)
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

    fun fetchProductById(productId: String, onProductFetched: (ProductItem?) -> Unit) {
        viewModelScope.launch {
            _productState.value = Resource.Loading()
            try {
                val product = repository.getProductById(productId)
                onProductFetched(product) // Return the fetched product via callback
            } catch (e: Exception) {
                _productState.value = Resource.Error("Failed to fetch product: ${e.message}")
            }
        }
    }

}

