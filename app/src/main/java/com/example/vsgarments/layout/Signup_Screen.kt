package com.example.vsgarments.layout

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.emreesen.sntoast.SnToast
import com.emreesen.sntoast.Type
import com.example.vsgarments.R
import com.example.vsgarments.authentication.RegisterViewModel
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.authentication.User
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.char_editText
import com.example.vsgarments.view_functions.customToast
import com.google.firebase.auth.FirebaseAuth
import java.net.URLEncoder

@Composable
fun Signup_Screen(
    modifier: Modifier,
    navController: NavController
) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var _password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    var triggeredByUser by rememberSaveable { mutableStateOf(false) }

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val viewModel: RegisterViewModel = hiltViewModel()
    val lifecycleOwner = LocalLifecycleOwner.current

    Box {

        Column(
            modifier = modifier
                .background(Color.White)
                .padding(
                    horizontal = 50.dp,
                    vertical = 30.dp
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {

            Spacer(modifier = Modifier.height(0.dp))

            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.MainScreen.route)
                    }
            )

            Spacer(
                modifier = Modifier
                    .height(0.dp)
            )

            Text(
                text = "Create your account",
                color = textcolorgrey,
                fontSize = 20.sp,
                fontFamily = fontInter,
                fontWeight = FontWeight.SemiBold
            )
            char_editText(Modifier,"Username", fontInter, name){
                name = it
            }
            char_editText(
                modifier = Modifier
                    .focusRequester(emailFocusRequester) ,
                "Email ",
                fontInter,
                email ,
                onTextChange = {
                    email = it
                }

            )
            char_editText(
                modifier = Modifier
                    .focusRequester(passwordFocusRequester) ,
                "Password",
                fontInter,
                _password ,
                onTextChange = {
                    _password = it
                }

            )
            char_editText(
                modifier = Modifier,
                "Confirm Password",
                fontInter,
                repeatPassword ,
                onTextChange = {
                    repeatPassword = it
                }

            )
            Spacer(
                modifier = Modifier
                    .height(0.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {

                blue_Button(
                    modifier = Modifier,
                    width_fraction = 0.5f,
                    button_text = "Register",
                    font_Family = fontBaloo,
                    onClick = {
                        if (name.isBlank() || email.isBlank() || _password.isBlank() || repeatPassword.isBlank()) {
                            errorMessage = "All fields are required."
                            return@blue_Button
                        }

                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            errorMessage = "Please enter a valid email address."
                            return@blue_Button
                        }

                        if (_password.length < 6) {
                            errorMessage = "Password must be at least 6 characters long."
                            return@blue_Button
                        }

                        if (_password != repeatPassword) {
                            errorMessage = "Passwords do not match."
                            return@blue_Button
                        }

                        triggeredByUser = true
                        isLoading = true
                        val user = User(
                            userName = name.trim(),
                            email = email.trim(),
                        )
                        val password = _password.trim()
                        viewModel.createAccountWithEmail(
                            user,
                            password
                        )

                        isLoading = false
                        val encodedEmail = URLEncoder.encode(user.email, "UTF-8")

                        navController.navigate("${Screen.EmailVerificationScreen.route}/$encodedEmail"){
                            popUpTo(Screen.Signup_Screen.route) { inclusive = true }
                        }
                    }
                )
            }

            LaunchedEffect(lifecycleOwner) {
                viewModel.register.collect {
                    if (triggeredByUser) {
                        when (it) {
                            is Resource.Error -> {
                                isLoading = false
                                errorMessage = it.errorMassage
                                Log.d(
                                    "register error",
                                    "Signup_Screen: ${it.errorMassage}"
                                )
                            }

                            is Resource.Loading -> {
                                isLoading = true
                            }

                            is Resource.Success -> {
                                isLoading = false
                                Log.d(
                                    "test",
                                    "Signup_Screen: ${it.data}"
                                )
                            }
                            else -> Unit
                        }
                        triggeredByUser = false
                    }
                }
            }


            val context = LocalContext.current
            LaunchedEffect(errorMessage) {
                errorMessage?.let {

                    customToast(context , it)
                    errorMessage = null
                }
            }

        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = topbardarkblue,
                    strokeWidth = 4.dp
                )
            }
        }

    }
}
