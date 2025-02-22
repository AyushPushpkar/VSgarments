package com.example.vsgarments.address

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.ADDRESS_COLLECTION
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _addNewAddress = MutableStateFlow<Resource<Address>>(Resource.Unspecified())
    val addNewAddress = _addNewAddress.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun addAddress(address: Address) {
        val validateInputs = validateInputs(address)

        if (!validateInputs) {
            viewModelScope.launch {
                _error.emit("All fields are required")
            }
            return
        }

        val userId = firebaseAuth.uid
        if (userId == null) {
            viewModelScope.launch {
                _addNewAddress.emit(Resource.Error("User not authenticated"))
            }
            return
        }


        viewModelScope.launch {
            _addNewAddress.emit(Resource.Loading())
            try {
                val addressRef = firestore.collection(USER_COLLECTION)
                    .document(userId)
                    .collection(ADDRESS_COLLECTION)
                    .document()

                addressRef.set(address).await()

                _addNewAddress.emit(Resource.Success(address))

            } catch (e: FirebaseFirestoreException) {
                _addNewAddress.emit(Resource.Error("Firestore error: ${e.message}"))
                Log.e("FirestoreError", "Failed to add address", e)
            } catch (e: Exception) {
                _addNewAddress.emit(Resource.Error("Unexpected error: ${e.message}"))
                Log.e("UnexpectedError", "Unexpected failure", e)
            }
        }


    }

    private fun validateInputs(address: Address): Boolean {
        return address.addressTitle.isNotEmpty() &&
                address.fullName.isNotEmpty() &&
                address.street.isNotEmpty() &&
                address.phone.isNotEmpty() &&
                address.city.isNotEmpty() &&
                address.state.isNotEmpty()
    }
}