package com.example.vsgarments.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.CART_COLLECTION
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore ,
    private val auth: FirebaseAuth ,
    private val firebaseCommon: FirebaseCommon
) : ViewModel(){

    private val _addToCart = MutableStateFlow<Resource<CartIItem>>(Resource.Unspecified())
    val addToCart: MutableStateFlow<Resource<CartIItem>> = _addToCart

    fun addUpdateProductInCart(cartProduct: CartIItem){
        viewModelScope.launch {
            _addToCart.emit(Resource.Loading())
            try {
                val cartRef = firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(CART_COLLECTION)

                val querySnapshot = cartRef.whereEqualTo("productItem.id", cartProduct.productItem.id).get().await() // if product exists in cart

                if (!querySnapshot.isEmpty) {
                    // Product exists, update the quantity
                    val document = querySnapshot.documents.first()
                    val existingProduct = document.toObject(CartIItem::class.java)

                    if (existingProduct != null && existingProduct.productItem == cartProduct.productItem) {
                        val currentQuantity = existingProduct.quantity
                        val maxQuantity = existingProduct.productItem.maxQuantity

                        if (currentQuantity < maxQuantity) {
                            increaseQuantity(document.id, cartProduct)
                        } else {
                            _addToCart.emit(Resource.Error("Cannot add more. Max quantity reached."))
                        }
                    } else {
                        addNewProduct(cartProduct)
                    }

                } else {
                    // Product does not exist, add it to cart
                    addNewProduct(cartProduct)
                }
            }catch (e : Exception){
                _addToCart.emit(Resource.Error(e.message ?: "Failed to save user data. Please try again later."))
            }
        }
    }

    private fun addNewProduct(cartProduct: CartIItem) {
        firebaseCommon.addProductToCart(cartProduct) { addedProduct, e ->
            viewModelScope.launch {
                if (e == null){
                    _addToCart.emit(Resource.Success(addedProduct!!))
                }else{
                    _addToCart.emit(Resource.Error(e.message.toString()))
                }
            }
        }
    }

    private fun increaseQuantity(documentId: String , cartProduct: CartIItem){
        firebaseCommon.increaseQuantity(documentId) { _, e ->
            viewModelScope.launch {
                if (e == null) {
                    _addToCart.emit(Resource.Success(cartProduct))
                } else {
                    _addToCart.emit(Resource.Error(e.message.toString()))
                }
            }
        }
    }
}