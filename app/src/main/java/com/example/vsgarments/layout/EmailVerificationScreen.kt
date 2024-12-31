package com.example.vsgarments.layout

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emreesen.sntoast.SnToast
import com.emreesen.sntoast.Type
import com.example.vsgarments.R
import com.example.vsgarments.authentication.util.VerificationStatus
import com.example.vsgarments.authentication.util.VerificationStatusSaver
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.customToast
import com.google.firebase.auth.FirebaseAuth

@Composable
fun EmailVerificationScreen(
    navController: NavController,
    modifier: Modifier = Modifier ,
    userEmail: String
) {

    var status by rememberSaveable(stateSaver = VerificationStatusSaver) {
        mutableStateOf(VerificationStatus.Idle)
    }

    val auth = remember { FirebaseAuth.getInstance() }
    val context = LocalContext.current

    var isEmailVerified by remember { mutableStateOf(auth.currentUser?.isEmailVerified ?: false) }

    Box{
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
                text = "Verify Your Email",
                color = textcolorgrey,
                fontSize = 20.sp,
                fontFamily = fontInter,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(0.dp))

            Column {

                Text(
                    text = "A verification email has been sent to:",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = tintGrey
                )

                Text(
                    text = userEmail ,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = tintGrey
                )
            }

            Spacer(modifier = Modifier.height(0.dp))

            when (val currentStatus = status) {
                is VerificationStatus.Loading -> {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth() ,
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(
                            color =  topbardarkblue,
                        )
                    }
                }
                is VerificationStatus.Success -> {
                    Text(
                        text = "Verification email sent successfully!",
                        color = textcolorblue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                is VerificationStatus.Error -> {
                    Text(
                        text = currentStatus.message,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
                else -> {}
            }

            Spacer(modifier = Modifier.height(0.dp))

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    blue_Button(
                        onClick = {
                            status = VerificationStatus.Loading
                            checkEmailVerificationStatus(
                                auth,
                                context,
                                navController,
                                { newStatus ->
                                    status = newStatus
                                },
                                { newVerifiedStatus ->
                                    isEmailVerified = newVerifiedStatus
                                })
                        },
                        font_Family = fontBaloo,
                        width_fraction = 0.8f,
                        modifier = Modifier,
                        button_text =
                        "Check Verification"
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    blue_Button(
                        onClick = {
                            status = VerificationStatus.Loading
                            resendVerificationEmail(
                                auth,
                                context,
                                updateStatus = { status = it })
                        },
                        font_Family = fontBaloo,
                        width_fraction = 0.8f,
                        modifier = Modifier,
                        button_text =
                        "Resend Verification Email"

                    )
                }
            }
        }
    }
}

fun checkEmailVerificationStatus(
    auth: FirebaseAuth,
    context: Context,
    navController: NavController,
    updateStatus: (VerificationStatus) -> Unit,
    updateIsEmailVerified: (Boolean) -> Unit
) {
    auth.currentUser?.reload()?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val isVerified = auth.currentUser?.isEmailVerified == true
            updateIsEmailVerified(isVerified)

            if (isVerified) {
                customToast(context , "Email verified. You can now log in.")
                navController.navigate(Screen.MainScreen.route)
            } else {
                customToast(context , "Email not verified yet. Please check your email.")
            }
            updateStatus(VerificationStatus.Success)
        } else {
            customToast(context , "Failed to check verification status: ${task.exception?.message}")
            updateStatus(VerificationStatus.Error("Failed to check status."))
        }
    }
}


fun resendVerificationEmail(auth: FirebaseAuth, context: Context , updateStatus: (VerificationStatus) -> Unit) {
    auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            customToast(context , "Verification email resent.")
            updateStatus(VerificationStatus.Success)
        } else {
            customToast(context, "Failed to resend verification email: ${task.exception?.message}")
        }
    }
}




