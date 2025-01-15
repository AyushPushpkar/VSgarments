package com.example.vsgarments.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontBaloo

import com.example.vsgarments.view_functions.blue_Button


@Composable
fun IntroductionScreen(
    modifier: Modifier ,
    navController: NavController
) {

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

            Spacer(modifier = Modifier.height(100.dp))

            blue_Button(
                width_fraction = 1f,
                button_text = "Login",
                font_Family = fontBaloo
            ) {
                navController.navigate(Screen.Login_Screen.route){
                    popUpTo(Screen.IntroductionScreen.route){ inclusive = true}
                }
            }
            blue_Button(
                width_fraction = 1f,
                button_text = "SignUP",
                font_Family = fontBaloo
            ) {
                navController.navigate(Screen.Signup_Screen.route){
                    popUpTo(Screen.IntroductionScreen.route){ inclusive = true}
                }
            }
        }

    }
}