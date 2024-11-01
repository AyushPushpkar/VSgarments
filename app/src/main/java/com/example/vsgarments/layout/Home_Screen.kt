package com.example.vsgarments.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vsgarments.navigation.App_Navigation
import com.example.vsgarments.navigation.BottomNavBar
import com.example.vsgarments.navigation.Bottom_Nav_item
import com.example.vsgarments.navigation.Bottom_Navigation
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor

@Composable
fun HomeScreen(
    modifier: Modifier ,
    navController: NavController
){

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ){
        Text(text = "Home Screen")
    }

}