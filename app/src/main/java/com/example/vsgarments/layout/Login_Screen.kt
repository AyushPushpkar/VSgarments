package com.example.vsgarments.layout

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.authentication.LoginViewModel
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.char_editText
import com.example.vsgarments.view_functions.customToast
import com.example.vsgarments.view_functions.number_editText
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier
) {

    var _email by rememberSaveable { mutableStateOf("") }
    var _password by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val loginViewModel : LoginViewModel = hiltViewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Box (
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ){
        var initiallyOpened by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(
                    horizontal = 50.dp,
                    vertical = 30.dp
                )
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .height(0.dp)
            )

            Text(
                text = "Login to your account",
                color = textcolorgrey,
                fontSize = 20.sp,
                fontFamily = fontInter,
                fontWeight = FontWeight.SemiBold
            )
            var isEmail by remember { mutableStateOf(true) }

            Column {
//                if (isEmail) {
//                    char_editText(
//                        modifier = Modifier,
//                        hint = "Email ",
//                        font_Family = fontInter,
//                        text = _email,
//                        onTextChange = {
//                            _email = it
//                        }
//
//                    )
//                } else {
//                    number_editText(
//                        hint = "Mobile Number",
//                        char_no = 10,
//                        font_Family = fontInter
//                    )
//                }
                char_editText(
                    modifier = Modifier,
                    hint = "Email ",
                    font_Family = fontInter,
                    text = _email,
                    onTextChange = {
                        _email = it
                    },
                    focusRequester = focusRequester
                )

                Spacer(modifier = Modifier.height(5.dp))

            }

            char_editText(
                modifier = Modifier,
                "Password",
                fontInter,
                _password,
                onTextChange = {
                    _password = it
                } ,
                focusRequester = focusRequester
            )


            Row {
                Text(
                    text = "Forget your password ? ",
                    color = textcolorgrey,
                    fontSize = 13.sp,
                    fontFamily = fontInter,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = " Reset here",
                    color = topbardarkblue,
                    fontSize = 13.sp,
                    fontFamily = fontInter,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .clickable {
                            initiallyOpened = true
                        }
                )
            }
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
                    button_text = "Login",
                    font_Family = fontBaloo,
                    onClick = {
                        if (_email.isBlank() || _password.isBlank()) {
                            isLoading = false
                            errorMessage = "All fields are required."
                            return@blue_Button
                        }

                        if (!Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
                            isLoading = false
                            errorMessage = "Please enter a valid email address."
                            return@blue_Button
                        }

                        if (_password.length < 6) {
                            isLoading = false
                            errorMessage = "Password must be at least 6 characters long."
                            return@blue_Button
                        }

                        isLoading = true
                        val email = _email.trim()
                        val password = _password.trim()

                        loginViewModel.loginWithEmailPassword(
                            email,
                            password
                        )

                    }
                )
            }

            var textLayoutResult: TextLayoutResult? by remember {
                mutableStateOf(null)
            }

            val annotatedText = buildAnnotatedString {
                append("Don't have an account? ")

                pushStringAnnotation(
                    tag = "SignUp",
                    annotation = "Sign Up"
                )
                withStyle(
                    style = SpanStyle(
                        color = topbardarkblue,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Sign Up")
                }
                pop()

            }

            Text(
                text = annotatedText,
                style = TextStyle(
                    color = textcolorgrey,
                    fontSize = 16.sp
                ),
                onTextLayout = {
                    textLayoutResult = it
                },
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures { offsetPosition ->
                        textLayoutResult?.let { layoutResult ->
                            val position = layoutResult.getOffsetForPosition(offsetPosition)
                            annotatedText.getStringAnnotations(
                                tag = "SignUp",
                                start = position,
                                end = position
                            )
                                .firstOrNull()?.let {
                                    navController.navigate(Screen.Signup_Screen.route)
                                }
                        }
                    }
                }
            )

            LaunchedEffect(lifecycleOwner) {
                loginViewModel.login.collect {
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

                            val message = it.data
                            errorMessage = message

                            navController.navigate(Screen.MainScreen.route)
                        }

                        is Resource.Unspecified -> TODO()
                    }
                }

            }

            LaunchedEffect(errorMessage) {
                errorMessage?.let {

                    customToast(
                        context,
                        it ,
                        true
                    )
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

        ForgotPasswordDialog(
            navController = navController,
            initiallyOpened = initiallyOpened,
            onDismissRequest = {
                initiallyOpened = false
            } ,
            lifecycleOwner = lifecycleOwner ,
            context = context
        )
    }
}

@Composable
fun ForgotPasswordDialog(
    navController: NavController,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean,
    onDismissRequest: () -> Unit,
    lifecycleOwner: LifecycleOwner ,
    context: Context
) {

    var _email by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val loginViewModel : LoginViewModel = hiltViewModel()

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    AnimatedVisibility(
        visible = initiallyOpened,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x4DB6E9FF))
                .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(Color.Transparent)
                    .clickable { onDismissRequest() }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .background(Color.White)
                    .padding(35.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {}  // Consume taps
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { _, _ -> /* Consume drag gestures */ }
                    },
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .height(0.dp)
                )

                Row {
                    Text(
                        modifier = Modifier.weight(9f),
                        text = "Reset Password",
                        color = textcolorgrey,
                        fontSize = 20.sp,
                        fontFamily = fontInter,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onDismissRequest()
                            },
                        painter = painterResource(id = R.drawable.edit_pen),
                        contentDescription = "cross icon"
                    )
                }

                Text(
                    text = "We will send you password reset link on your Email",
                    color = textcolorgrey,
                    fontSize = 15.sp,
                    fontFamily = fontInter,
                    fontWeight = FontWeight.Medium
                )

                char_editText(
                    modifier = Modifier,
                    hint = "Email",
                    font_Family = fontInter,
                    text = _email ,
                    onTextChange = {_email = it},
                    focusRequester = focusRequester
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
                        button_text = "Send",
                        font_Family = fontBaloo ,
                        onClick = {
                            isLoading = true
                            val email = _email.trim()
                            loginViewModel.resetPassword(email)
                        }
                    )
                }

            }

            LaunchedEffect(lifecycleOwner) {
                loginViewModel.resetPassword.collect {
                    when (it) {
                        is Resource.Error -> {
                            isLoading = false
                            errorMessage = it.errorMassage
                        }

                        is Resource.Loading -> {
                            isLoading = true
                        }

                        is Resource.Success -> {
                            isLoading = false

                            val message = it.data ?: "Reset email sent successfully."
                            errorMessage = message

                            delay(2000)
                            onDismissRequest()

                        }

                        is Resource.Unspecified -> TODO()
                    }
                }

            }

            LaunchedEffect(errorMessage) {
                errorMessage?.let {

                    customToast(
                        context,
                        it
                    )
                    errorMessage = null
                }
            }
        }
    }
}



