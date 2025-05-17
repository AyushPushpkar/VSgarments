package com.example.vsgarments.authentication

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vsgarments.authentication.util.Constants.USER_COLLECTION
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.product.getRealPathFromURI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.appwrite.ID
import io.appwrite.Permission
import io.appwrite.models.InputFile
import io.appwrite.services.Storage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/* The UI layer (composable) can observe the register state and update accordingly:
Show a loading indicator when Loading is emitted.
Display the user details when Success is emitted.
Show an error message when Error is emitted. */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth ,
    private val userDb: FirebaseFirestore ,
    private val appWriteStorage: Storage ,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val userCollection = userDb.collection(USER_COLLECTION)

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified())
    val register: StateFlow<Resource<FirebaseUser>> = _register

    private val _currentUser = MutableStateFlow<Resource<User>>(Resource.Unspecified()) // Resource for current user
    val currentUser: StateFlow<Resource<User>> = _currentUser

    private val _savedImagePath = MutableStateFlow<String?>(null)
    val savedImagePath: StateFlow<String?> = _savedImagePath

    init {
        fetchCurrentUser()
        _savedImagePath.value = getSavedImagePath()
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

    fun updateUserDetails(updatedFields: Map<String, Any?>) {
        viewModelScope.launch {
            val firebaseUser = firebaseAuth.currentUser
            firebaseUser?.uid?.let { uid ->
                try {

                    userDb.collection(USER_COLLECTION)
                        .document(uid)
                        .update(updatedFields)
                        .await()

                    // Emit a loading state before fetching the updated user data
                    _currentUser.emit(Resource.Loading())

                    fetchCurrentUser()  //the updated user data

                } catch (e: Exception) {
                    _currentUser.emit(Resource.Error(e.message ?: "Failed to update user details."))
                }
            } ?: run {
                _currentUser.emit(Resource.Error("No user signed in."))
            }
        }
    }


    fun logout(context: Context) {
        viewModelScope.launch {
            try {
                firebaseAuth.signOut()
                clearLocalPrefs()

                val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()

                // Clear current user state
                _currentUser.emit(Resource.Error("User logged out.")) // Emit logout state
            } catch (e: Exception) {
                _currentUser.emit(Resource.Error(e.message ?: "Failed to log out."))
            }
        }
    }

    private fun clearLocalPrefs() {
        val sharedPreferences = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
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


    // ✅ Save image locally
    fun handleProfileImageSave(userId: String, imageUri: Uri) {
        viewModelScope.launch {
            try {
                val imagePath = saveImageLocally(imageUri, userId)
                saveImagePathToPrefs(imagePath)
                _savedImagePath.emit(null)             // <--- Force re-emit
                delay(10)                             // Optional small delay
                _savedImagePath.emit(imagePath)
            } catch (e: Exception) {
                Log.e("ProfileImage", "Failed to add profile image: ${e.message}")
            }
        }
    }

    private fun saveImageLocally(imageUri: Uri, userId: String ): String {
        val inputStream = appContext.contentResolver.openInputStream(imageUri)
            ?: throw IllegalArgumentException("Unable to open image URI")
        val fileName = "$userId-profile.jpg"
        val file = File(appContext.filesDir, fileName)

        inputStream.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }

    private fun saveImagePathToPrefs(imagePath: String) {
        val prefs = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("local_profile_image_path", imagePath).apply()
    }

    // ✅ Optional: retrieve saved image path
    fun getSavedImagePath(): String? {
        val prefs = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return prefs.getString("local_profile_image_path", null)
    }


}


