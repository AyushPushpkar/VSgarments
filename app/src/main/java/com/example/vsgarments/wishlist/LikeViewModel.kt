package com.example.vsgarments.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.CART_COLLECTION
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Constants.WISHLIST_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.cart.CartIItem
import com.example.vsgarments.cart.FirebaseCommon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val firebaseCommon: FirebaseCommon
) : ViewModel(){

    private val _addToWishlist = MutableStateFlow<Resource<WishlistItem>>(Resource.Unspecified())
    val addToWishlist: MutableStateFlow<Resource<WishlistItem>> = _addToWishlist

    fun addProductInWishlist(wishlistItem: WishlistItem){
        viewModelScope.launch {
            _addToWishlist.emit(Resource.Loading())
            try {
                val wishlistRef = firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(WISHLIST_COLLECTION)

                val querySnapshot = wishlistRef.whereEqualTo("productItem.id", wishlistItem.productItem.id).get().await() // if product exists in cart

                if (!querySnapshot.isEmpty) {
                    // Product exists, update the quantity
                    val document = querySnapshot.documents.first()
                    val existingProduct = document.toObject(WishlistItem::class.java)

                    if (existingProduct != null && existingProduct == wishlistItem) {
                        _addToWishlist.emit(Resource.Error("Item already in Wishlist"))
                    } else {
                        addNewProduct(wishlistItem)
                    }

                } else {
                    // Product does not exist, add it to cart
                    addNewProduct(wishlistItem)
                }
            }catch (e : Exception){
                _addToWishlist.emit(Resource.Error(e.message ?: "Failed to save user data. Please try again later."))
            }
        }
    }

    private fun addNewProduct(wishlistItem: WishlistItem) {
        firebaseCommon.addProductToWishlist(wishlistItem) { addedProduct, e ->
            viewModelScope.launch {
                if (e == null){
                   _addToWishlist.emit(Resource.Success(addedProduct!!))
                }else{
                    _addToWishlist.emit(Resource.Error(e.message.toString()))
                }
            }
        }
    }
}