package com.example.vsgarments.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class Bottom_Nav_item(
    val name : String,
    val route : String,
    val icon : ImageVector,
    val badge : Int = 0
)
