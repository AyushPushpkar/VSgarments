package com.example.vsgarments.navigation

sealed class Screen(val route: String) {

    data object Profile_Screen : Screen("profile_screen")
    data object EditProfile_Screen : Screen("edit_profile_screen")
    data object Login_Screen : Screen("login_screen")
    data object Signup_Screen : Screen("signup_screen")
    data object Home_Screen : Screen("home_screen")
    data object Settings_Screen : Screen("settings_screen")
    data object Splash_Screen : Screen("splash_screen")
}