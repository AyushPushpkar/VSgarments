package com.example.vsgarments.layout

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontKalnia
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.splashlightblue
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


@Composable
fun Splash_Screen(
    modifier: Modifier ,
    navController: NavController
){

    LaunchedEffect(Unit) {
        val auth = FirebaseAuth.getInstance()
        delay(30) // delay for 3 seconds or as needed

        if (auth.currentUser == null) {
            navController.navigate(Screen.IntroductionScreen.route) {
                popUpTo(Screen.Splash_Screen.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.MainScreen.route) {
                popUpTo(Screen.Splash_Screen.route) { inclusive = true }
            }
        }
    }

    var heightstate by remember {
        mutableFloatStateOf(0f)
    }

    val height by animateFloatAsState(
        targetValue = heightstate ,
        tween(
            durationMillis = 1950 ,
            delayMillis = 50 ,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(Unit) {
        heightstate = 0.8f
    }

    Box (
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        splashlightblue,
                        splashdarkblue
                    )
                )
            ),
    ){
        Box(modifier = Modifier
            .fillMaxHeight(.8f)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier
                    .fillMaxHeight(height),
                painter = painterResource(id = R.drawable.white_logo),
                contentDescription = "Aryan")
        }
        Box(modifier = Modifier
            .fillMaxHeight(.90f)
            .fillMaxWidth()
            .padding(30.dp),
            contentAlignment = Alignment.BottomCenter
        ){
              Text(
                  text = "VS Garments",
                  fontSize = 38.sp ,
                  color = Color.White ,
                  fontFamily = fontKalnia ,
                  fontWeight = FontWeight.Bold
              )
        }


    }

}