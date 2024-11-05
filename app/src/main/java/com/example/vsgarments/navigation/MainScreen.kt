package com.example.vsgarments.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    modifier: Modifier ,
    navController: NavController
){
    val bottomNavController = rememberNavController()
    Scaffold(
        modifier = modifier.fillMaxSize() ,
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    Bottom_Nav_item(
                        name = "Home" ,
                        route = "home" ,
                        icon = Icons.Default.Home
                    ) ,
                    Bottom_Nav_item(
                        name = "Category" ,
                        route = "category" ,
                        icon = Icons.Default.Person
                    ) ,
                    Bottom_Nav_item(
                        name = "Order" ,
                        route = "order" ,
                        icon = Icons.Default.ShoppingCart ,
                        badge = 12
                    ) ,

                ),
                navController = bottomNavController ,
                onItemClick = {
                    bottomNavController.navigate(it.route){
                        popUpTo(bottomNavController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Bottom_Navigation(modifier = Modifier.padding(innerPadding) , bottomnavController = bottomNavController , navController = navController)
    }

}