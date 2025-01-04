package com.example.vsgarments.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth ,
    private val userDb: FirebaseFirestore
) : ViewModel(){

    private val _login = MutableSharedFlow<Resource<String>>()  // used to send one time event to the ui , to take action : navigate , snackbar etc
    val login = _login.asSharedFlow()

    private val _resetPassword = MutableSharedFlow<Resource<String>>()
    val resetPassword = _resetPassword.asSharedFlow()

    private val _currentUser = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val currentUser: StateFlow<Resource<User>> = _currentUser

    fun loginWithEmailPassword(email : String , password : String){

        viewModelScope.launch {
            _login.emit(Resource.Loading())

            try {
                val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                val firebaseUser = authResult.user

                if (firebaseUser != null) {
                    _login.emit(Resource.Success("Login successful"))
                } else {
                    _login.emit(Resource.Error("Login failed: User data is missing"))
                }
            } catch (e: FirebaseAuthInvalidUserException) {
                _login.emit(Resource.Error("Invalid user. Please check your credentials."))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _login.emit(Resource.Error("Invalid email or password."))
            } catch (e: Exception) {
                _login.emit(Resource.Error("An unexpected error occurred: ${e.message}"))
            }
        }
    }

    fun resetPassword(email: String) {

        viewModelScope.launch {
            try {
                firebaseAuth.sendPasswordResetEmail(email).await()
                _resetPassword.emit(Resource.Success("Password reset email sent"))
            } catch (e: FirebaseAuthInvalidUserException) {
                _resetPassword.emit(Resource.Error("No account found with this email"))
            } catch (e: FirebaseAuthException) {
                _resetPassword.emit(Resource.Error("An authentication error occurred: ${e.message}"))
            } catch (e: Exception) {
                _resetPassword.emit(Resource.Error("Password reset failed: ${e.message}"))
            }
        }
    }


}