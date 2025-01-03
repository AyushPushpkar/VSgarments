package com.example.vsgarments.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/* The UI layer (composable) can observe the register state and update accordingly:
Show a loading indicator when Loading is emitted.
Display the user details when Success is emitted.
Show an error message when Error is emitted. */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth ,
    private val userDb: FirebaseFirestore
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified())
    val register: StateFlow<Resource<FirebaseUser>> = _register

    private val _currentUser = MutableStateFlow<Resource<User>>(Resource.Unspecified()) // Resource for current user
    val currentUser: StateFlow<Resource<User>> = _currentUser

    init {
        fetchCurrentUser()
    }

    fun createAccountWithEmail(user: User, password: String) {
        viewModelScope.launch {
            _register.emit(Resource.Loading())
            try {
                // Attempt to register the user with email and password
                val authResult = firebaseAuth.createUserWithEmailAndPassword(user.email, password).await()
                val firebaseUser = authResult.user

                if (firebaseUser != null) {
                    saveUserInfo(firebaseUser, user)

                } else {
                    _register.emit(Resource.Error("Registration successful but user data is missing."))
                }
            } catch (e: FirebaseAuthUserCollisionException) {
                _register.emit(Resource.Error("This email is already in use. Please try another email."))
            } catch (e: FirebaseAuthWeakPasswordException) {
                _register.emit(Resource.Error("The password is too weak. Please use a stronger password."))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _register.emit(Resource.Error("The email address is invalid. Please provide a valid email."))
            } catch (e: Exception) {
                _register.emit(Resource.Error(e.message ?: "Registration failed. Please try again later."))
            }
        }
    }

    private fun saveUserInfo(firebaseUser: FirebaseUser, user: User) {
        viewModelScope.launch {
            try {
                // Save user data to Firestore
                userDb.collection(USER_COLLECTION)
                    .document(firebaseUser.uid)
                    .set(user)
                    .await() // Convert Firestore operation to coroutine

                // Send email verification
                firebaseUser.sendEmailVerification().await()

                // Emit success state
                _register.emit(Resource.Success(firebaseUser))
            } catch (e: Exception) {
                // Emit error state if Firestore operation or email verification fails
                _register.emit(Resource.Error(e.message ?: "Failed to save user data. Please try again later."))
            }
        }
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            _currentUser.emit(Resource.Loading()) // Emit loading state
            val firebaseUser = firebaseAuth.currentUser
            firebaseUser?.uid?.let { uid ->
                try {
                    val documentSnapshot = userDb.collection(USER_COLLECTION).document(uid).get().await()
                    val user = documentSnapshot.toObject(User::class.java)

                    if (user != null) {
                        _currentUser.emit(Resource.Success(user)) // Emit success state with user data
                    } else {
                        _currentUser.emit(Resource.Error("User data not found."))
                    }
                } catch (e: Exception) {
                    _currentUser.emit(Resource.Error(e.message ?: "Failed to fetch user data."))
                }
            } ?: run {
                _currentUser.emit(Resource.Error("No user signed in."))
            }
        }
    }


    private suspend fun pollEmailVerification(firebaseUser: FirebaseUser) {
        while (true) {
            firebaseUser.reload().await() // Refresh the user's status
            if (firebaseUser.isEmailVerified) {
                _register.emit(Resource.Success(firebaseUser))
                break
            }
            delay(3000) // Check every 3 seconds
        }
    }
}


