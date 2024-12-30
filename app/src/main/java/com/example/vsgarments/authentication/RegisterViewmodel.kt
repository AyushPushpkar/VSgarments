package com.example.vsgarments.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/* The UI layer (composable) can observe the register state and update accordingly:
Show a loading indicator when Loading is emitted.
Display the user details when Success is emitted.
Show an error message when Error is emitted. */

@HiltViewModel
class RegisterViewmodel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<FirebaseUser>> (Resource.Unspecified())
    val register : Flow<Resource<FirebaseUser>> = _register

    fun createAccountWithEmail(user : User , password : String){

        runBlocking {
            _register.emit(Resource.Loading())
        }

        firebaseAuth.createUserWithEmailAndPassword(user.email , password)
            .addOnSuccessListener {authResult ->
                authResult.user?.let {
                    _register.value = Resource.Success(it)
                }
            }
            .addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())
            }
    }
}