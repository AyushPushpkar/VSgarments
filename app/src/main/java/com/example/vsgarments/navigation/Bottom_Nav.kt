package com.example.vsgarments.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vsgarments.layout.EditProfile_Screen
import com.example.vsgarments.layout.HomeScreen
import com.example.vsgarments.layout.LoginScreen
import com.example.vsgarments.layout.Order_Screen
import com.example.vsgarments.layout.Profile_Screen
import com.example.vsgarments.layout.Signup_Screen
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

@Composable
fun Bottom_Navigation(
    modifier: Modifier,
    bottomnavController: NavHostController,
    navController: NavController,
) {

    NavHost(
        navController = bottomnavController,
        startDestination = "home2"
    ) {
        composable(route = "home") {
            HomeScreen(
                navController = navController,
                modifier = modifier
            )
        }
        composable(route = "home2") {
            Signup_Screen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(route = "home3") {
            Order_Screen(
                navController = navController,
                modifier = modifier
            )
        }
    }
}

@Composable
fun BottomNavBar(
    items: List<Bottom_Nav_item>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (Bottom_Nav_item) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val middleIndex = items.size / 2

    // Selected index and item based on navigation or fallback to middle index initially
    val selectedItem = items.find { it.route == backStackEntry.value?.destination?.route }
        ?: items[middleIndex]
    val selectedIndex = items.indexOf(selectedItem)

    // Animatable for circle position and size, starting at middleIndex
    val circleOffset = remember { Animatable(middleIndex.toFloat()) }
    val circleSize = remember { Animatable(1.2f) }

    // Update circle position and size based on selectedIndex
    LaunchedEffect(selectedIndex) {
        circleOffset.animateTo(
            selectedIndex.toFloat(),
            animationSpec = tween(durationMillis = 300)
        )
        circleSize.animateTo(
            1.2f,
            animationSpec = tween(durationMillis = 300)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                topbarlightblue,
                                topbardarkblue
                            )
                        )
                    )
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onItemClick(item)
                            }
                    ) {
                        // Use AnimatedVisibility to fade out the selected icon
                        AnimatedVisibility(
                            visible = index != selectedIndex,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }
        }

        // Animated Circle for Selected Tab
        val horizontalPadding = 16.dp // Adjust padding as needed
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val tabWidth = (screenWidth - horizontalPadding * 2) / items.size
        val circleOffsetX = (circleOffset.value * tabWidth.value - tabWidth.value).dp

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(
                    x = circleOffsetX,
                    y = 0.dp
                ) // Updated here
                .size(60.dp * circleSize.value) // Control the size of the circle
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            topbarlightblue,
                            topbardarkblue
                        )
                    )
                )
                .animateContentSize() // Automatically animate size changes
        ) {
            if (selectedIndex >= 0) {
                Icon(
                    imageVector = selectedItem.icon,
                    contentDescription = selectedItem.name,
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.Center) // Center the icon inside the circle
                )
            }
        }
    }
}

