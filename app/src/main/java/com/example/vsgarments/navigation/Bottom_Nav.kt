package com.example.vsgarments.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vsgarments.layout.EditProfile_Screen
import com.example.vsgarments.layout.LoginScreen
import com.example.vsgarments.layout.Order_Screen
import com.example.vsgarments.layout.Profile_Screen
import com.example.vsgarments.layout.Signup_Screen
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue

@Composable
fun Bottom_Navigation(modifier: Modifier , navController: NavHostController){

    NavHost(navController = navController, startDestination = "home2" ){
        composable(route = "home"){
            Profile_Screen(navController = navController , modifier = modifier)
        }
        composable(route = "home2"){
            EditProfile_Screen( modifier = modifier , navController = navController )
        }
        composable(route = "home3"){
            Order_Screen(navController = navController , modifier = modifier)
        }
    }
}

@Composable
fun BottomNavBar(
    items: List<Bottom_Nav_item>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (Bottom_Nav_item) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val selectedItem = items.find { it.route == backStackEntry.value?.destination?.route }

    Box(modifier = modifier.fillMaxWidth()) {
        // Bottom bar background
        BottomAppBar(
            containerColor = topbardarkblue,
            tonalElevation = 8.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            // Navigation items within the BottomAppBar
            items.forEach { item ->
                NavigationBarItem(
                    selected = item == selectedItem,
                    onClick = { onItemClick(item) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = if (item == selectedItem) tintGreen else Color.White
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Green,
                        unselectedIconColor = Color.Gray
                    )
                )
            }
        }

        // Floating circular bubble for the selected item
        if (selectedItem != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-100).dp) // Position above the BottomAppBar
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(topbardarkblue)
                    .border(10.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = selectedItem.icon,
                    contentDescription = selectedItem.name,
                    tint = Color.Green
                )
            }
        }
    }
}

