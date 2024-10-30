package com.example.vsgarments.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vsgarments.layout.EditProfile_Screen
import com.example.vsgarments.layout.Profile_Screen

@Composable
fun App_Navigation(modifier: Modifier){

    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.EditProfile_Screen.route
    ) {
        composable(route = Screen.Profile_Screen.route){
            Profile_Screen(navController = navController , modifier = modifier)
        }
        composable(route = Screen.EditProfile_Screen.route) {
            EditProfile_Screen(navController = navController , modifier = modifier)
        }
    }
}