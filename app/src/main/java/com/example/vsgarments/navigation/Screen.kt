package com.example.vsgarments.navigation

sealed class Screen(val route: String) {

    data object Profile_Screen : Screen("profile_screen")
    data object EditProfile_Screen : Screen("edit_profile_screen")
}