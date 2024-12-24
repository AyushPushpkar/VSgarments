package com.example.vsgarments.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vsgarments.layout.CartScreen
import com.example.vsgarments.layout.DisplayScreen
import com.example.vsgarments.layout.EditProfile_Screen
import com.example.vsgarments.layout.LoginScreen
import com.example.vsgarments.layout.Profile_Screen
import com.example.vsgarments.layout.Settings_Screen
import com.example.vsgarments.layout.Signup_Screen
import com.example.vsgarments.layout.Splash_Screen
import com.example.vsgarments.layout.Wishlist
import com.example.vsgarments.dataStates.ImageItem
import com.google.gson.Gson
import java.net.URLDecoder


@Composable
fun App_Navigation(modifier: Modifier ){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash_Screen.route
    ) {
        composable(route = Screen.Profile_Screen.route){
            Profile_Screen(navController = navController , modifier = modifier)
        }
        composable(route = Screen.EditProfile_Screen.route) {
            EditProfile_Screen(navController = navController , modifier = modifier)
        }
        composable(route = Screen.Login_Screen.route) {
            LoginScreen(navController= navController , modifier = modifier)
        }
        composable(route = Screen.Settings_Screen.route) {
            Settings_Screen(modifier = modifier , navController = navController)
        }
        composable(route = Screen.Splash_Screen.route) {
            Splash_Screen(modifier = modifier , navController = navController)
        }
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController , modifier = modifier)
        }
        composable(route = Screen.Signup_Screen.route){
            Signup_Screen(navController = navController , modifier = modifier)
        }
        composable(route = Screen.Wishlist.route){
            Wishlist(navController = navController , modifier = modifier)
        }
        composable(route = Screen.CartScreen.route){
            CartScreen(navController = navController , modifier = modifier)
        }
        composable(
            route = "${Screen.DisplayScreen.route}/{imageItem}" ,
            arguments = listOf(navArgument("imageItem") { type = NavType.StringType })
        ){
            // Retrieve and decode the JSON strings
            val imageItemJson = it.arguments?.getString("imageItem")

            // Deserialize the JSON strings to objects
            val imageItem = imageItemJson?.let { jsonString ->
                Gson().fromJson(URLDecoder.decode(jsonString, "UTF-8"), ImageItem::class.java)
            }
            DisplayScreen(
                modifier = modifier,
                navController = navController,
                imageItem = imageItem
            )
        }


    }
}
