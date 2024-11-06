package com.example.vsgarments.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomNavViewModel : ViewModel() {
    private val _bottomNavItems = listOf(
        Bottom_Nav_item("Home", "home", Icons.Default.Home),
        Bottom_Nav_item("Category", "category", Icons.Default.Person),
        Bottom_Nav_item("Order", "order", Icons.Default.ShoppingCart)
    )
    val bottomNavItems: List<Bottom_Nav_item> = _bottomNavItems

    private val _selectedRoute = MutableLiveData("home")
    val selectedRoute: LiveData<String> = _selectedRoute

    fun onItemClick(route: String) {
        _selectedRoute.value = route
    }
}
