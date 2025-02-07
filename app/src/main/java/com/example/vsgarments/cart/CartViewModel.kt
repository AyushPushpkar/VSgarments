package com.example.vsgarments.cart

import android.util.Log
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
import kotlinx.coroutines.tasks.await
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

    fun changeQuantity(cartProduct: CartIItem , quantity : Int){
        val index = cartProducts.value.data?.indexOf(cartProduct)

        if (index != null && index != -1) {
            val documentId = cartProductDoc[index].id
            firebaseCommon.changeQuantity(quantity, documentId) { result, exception ->
                if (exception != null)
                    viewModelScope.launch { _cartState.emit(Resource.Error(exception.message.toString())) }
            }

        }else {
            Log.e("CartViewModel", "Error: Product index not found in the cart!")
        }

    }

    fun incDecQuantity(cartProduct: CartIItem, quantityChanging: FirebaseCommon.QuantityChanging){

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
        }else {
            Log.e("CartViewModel", "Error: Product index not found in the cart!")
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

    fun removeFromCart(cartProduct: CartIItem) {
        viewModelScope.launch {
            try {
                val index = cartProducts.value.data?.indexOf(cartProduct)

                if (index != null && index != -1) {
                    val documentId = cartProductDoc[index].id

                    firestore.collection(USER_COLLECTION)
                        .document(auth.uid!!)
                        .collection(CART_COLLECTION)
                        .document(documentId)
                        .delete()

                    Log.d("CartViewModel", "Product removed successfully!")

                } else {
                    Log.e("CartViewModel", "Error: Product index not found in the cart!")
                }

            } catch (e: Exception) {
                Log.e("CartViewModel", "Error removing product: ${e.message}")
            }
        }
    }


}