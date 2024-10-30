package com.example.vsgarments.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun Bottom_bar(
    item: List<Bottom_Nav_item>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (Bottom_Nav_item) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent ,
        tonalElevation = 8.dp ,
    ) {
        item.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
        }
    }
}