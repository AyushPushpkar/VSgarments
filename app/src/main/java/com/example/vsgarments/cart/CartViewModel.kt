package com.example.vsgarments.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.CART_COLLECTION
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel(){

    private val _cartState = MutableStateFlow<Resource<List<CartIItem>>>(Resource.Unspecified())
    val cartProducts = _cartState.asStateFlow()

    private var cartProductDoc = emptyList<DocumentSnapshot>()

    init {
        getProducts()
    }

    private fun getProducts(){
        viewModelScope.launch {
            _cartState.emit(Resource.Loading())
            try {
                val cartRef = firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(CART_COLLECTION)

                cartRef.addSnapshotListener { value, error ->

                    if (error != null || value == null) {
                            viewModelScope.launch {
                                _cartState.emit(Resource.Error(error?.message.toString()))
                            }
                    }else {
                        cartProductDoc = value.documents
                        val cartProducts = value.toObjects(CartIItem::class.java)
                        viewModelScope.launch {
                            _cartState.emit(Resource.Success(cartProducts))
                        }
                    }

                }
            }catch (e : Exception){
                _cartState.emit(Resource.Error(e.message ?: "Failed to save user data. Please try again later."))
            }
        }

    }

    fun changeQuantity(cartProduct: CartIItem, quantityChanging: FirebaseCommon.QuantityChanging){

        val index = cartProducts.value.data?.indexOf(cartProduct)

        /**
         * Index might be equal to -1 if the function "getProducts" delays which will delay the result we expect inside the _cartState
         * and to prevent the app from crashing we make a check
         */

        if (index != null && index != -1) {
            val documentId = cartProductDoc[index].id

            when(quantityChanging) {
                FirebaseCommon.QuantityChanging.INCREASE -> {
                   increaseQuantity(documentId)
                }

                FirebaseCommon.QuantityChanging.DECREASE -> {
                    decreaseQuantity(documentId)
                }
            }
        }
    }

    private fun decreaseQuantity(documentId: String) {
        firebaseCommon.decreaseQuantity(documentId) { result, exception ->
            if (exception != null)
                viewModelScope.launch { _cartState.emit(Resource.Error(exception.message.toString())) }
        }
    }

    private fun increaseQuantity(documentId: String) {
        firebaseCommon.increaseQuantity(documentId) { result, exception ->
            if (exception != null)
                viewModelScope.launch { _cartState.emit(Resource.Error(exception.message.toString())) }
        }

    }
}