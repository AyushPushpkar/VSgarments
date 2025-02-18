package com.example.vsgarments.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.CART_COLLECTION
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Constants.WISHLIST_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.cart.CartIItem
import com.example.vsgarments.cart.FirebaseCommon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel(){

    private val _wishlistState = MutableStateFlow<Resource<List<WishlistItem>>>(Resource.Unspecified())
    val wishlistProducts = _wishlistState.asStateFlow()

    private var wishlistProductDoc = emptyList<DocumentSnapshot>()

    init {
        getProducts()
    }

    private fun getProducts(){
        viewModelScope.launch {
            _wishlistState.emit(Resource.Loading())
            try {
                val wishlistRef = firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(WISHLIST_COLLECTION)

                wishlistRef.addSnapshotListener { value, error ->

                    if (error != null || value == null) {
                        viewModelScope.launch {
                            _wishlistState.emit(Resource.Error(error?.message.toString()))
                        }
                    }else {
                        wishlistProductDoc = value.documents
                        val wishlistProducts = value.toObjects(WishlistItem::class.java)
                        viewModelScope.launch {
                            _wishlistState.emit(Resource.Success(wishlistProducts))
                        }
                    }

                }
            }catch (e : Exception){
                _wishlistState.emit(Resource.Error(e.message ?: "Failed to save user data. Please try again later."))
            }
        }

    }

    fun removeFromWishlist(wishlistItem: WishlistItem) {
        viewModelScope.launch {
            try {
                val index = wishlistProducts.value.data?.indexOf(wishlistItem)

                if (index != null && index != -1) {
                    val documentId = wishlistProductDoc[index].id

                    firestore.collection(USER_COLLECTION)
                        .document(auth.uid!!)
                        .collection(WISHLIST_COLLECTION)
                        .document(documentId)
                        .delete()

                    Log.d("WishlistViewModel", "Product removed successfully!")

                } else {
                    Log.e("WishlistViewModel", "Error: Product index not found in the wishlist!")
                }

            } catch (e: Exception) {
                Log.e("WishlistViewModel", "Error removing product: ${e.message}")
            }
        }
    }
}