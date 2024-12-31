package com.example.vsgarments.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/* The UI layer (composable) can observe the register state and update accordingly:
Show a loading indicator when Loading is emitted.
Display the user details when Success is emitted.
Show an error message when Error is emitted. */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified())
    val register: StateFlow<Resource<FirebaseUser>> = _register

    fun createAccountWithEmail(user: User, password: String) {
        viewModelScope.launch {
            _register.emit(Resource.Loading())
            try {
                firebaseAuth.createUserWithEmailAndPassword(user.email, password).await()
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    firebaseUser.sendEmailVerification().await() // Await email verification sent
                    _register.emit(Resource.Success(firebaseUser))

                    // Poll the email verification status
//                    pollEmailVerification(firebaseUser)
//                    _register.emit(Resource.Success(firebaseUser))
                } else {
                    _register.emit(Resource.Error("User registration failed"))
                }
            } catch (e: Exception) {
                _register.emit(Resource.Error(e.message ?: "Registration failed"))
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


