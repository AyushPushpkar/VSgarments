package com.example.vsgarments.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.view_functions.blue_Button

@Composable
fun CartScreen(
    modifier: Modifier,
    navController: NavController
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor),
        contentAlignment = Alignment.Center
    ){
        blue_Button(
            width_fraction = 0.6f,
            button_text = "Sign Up",
            font_Family = fontBaloo,
        ) {
            navController.navigate(Screen.Signup_Screen.route)
        }
    }
}