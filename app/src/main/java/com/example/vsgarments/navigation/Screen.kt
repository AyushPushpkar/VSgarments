package com.example.vsgarments.navigation

sealed class Screen(val route: String) {

    data object Profile_Screen : Screen("profile_screen")
    data object EditProfile_Screen : Screen("edit_profile_screen")
    data object Login_Screen : Screen("login_screen")
    data object Signup_Screen : Screen("signup_screen")
    data object Settings_Screen : Screen("settings_screen")
    data object Splash_Screen : Screen("splash_screen")
    data object MainScreen : Screen("main_screen")
    data object CartScreen : Screen("cart_screen")
    data object Wishlist:Screen("wishlist")
    data object DisplayScreen : Screen("display_screen")
    data object IntroductionScreen : Screen("introduction_screen")
    data object EmailVerificationScreen : Screen("email_verification_screen")
    data object AddressScreen : Screen("address_screen")
    data object UpdateProductScreen : Screen("update_product_screen")
//    data object Payment_Screen:Screen(route = "payment_screen")
    data object AddProductScreen:Screen(route = "add_product_screen")
}