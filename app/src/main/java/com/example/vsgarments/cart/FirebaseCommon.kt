package com.example.vsgarments.cart

import com.example.vsgarments.authentication.util.Constants.CART_COLLECTION
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.dataStates.CartIItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseCommon(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth

) {

    private val cartCollection = firestore.collection(USER_COLLECTION).document(auth.uid!!).collection(CART_COLLECTION)

    fun addProductToCart (cartProduct: CartIItem, onResult: (CartIItem?, Exception?) -> Unit) {
        cartCollection.document().set(cartProduct)
            .addOnSuccessListener {
                onResult(cartProduct, null)
            }.addOnFailureListener {
                onResult(
                    null,
                    it
                )
            }
    }

    fun increaseQuantity(documentId: String, onResult: (String?, Exception?) -> Unit) {
        firestore.runTransaction { transition -> // all the operations happens at once
            val documentRef = cartCollection.document(documentId)
            val document = transition.get(documentRef)
            val productObject = document.toObject(CartIItem::class.java)

            productObject?.let {
                val newQuantity = it.quantity + 1
                val newProductObject = it.copy(quantity = newQuantity)
                transition.set(documentRef, newProductObject)

            }
        }.addOnSuccessListener {
            onResult(documentId, null)
        }.addOnFailureListener {
            onResult(null, it)
        }
    }
}