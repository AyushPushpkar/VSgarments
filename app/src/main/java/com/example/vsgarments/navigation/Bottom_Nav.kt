package com.example.vsgarments.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
import com.example.vsgarments.view_functions.BottomBarShape

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vsgarments.layout.CartScreen
import kotlinx.coroutines.async

@Composable
fun Bottom_Navigation(
    modifier: Modifier,
    bottomnavController: NavHostController,
    navController: NavController,
) {

    NavHost(
        navController = bottomnavController,
        startDestination = "home"
    ) {
        composable(route = "category") {
            CartScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(route = "home") {
            HomeScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(route = "order") {
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

    val selectedItem = items.find { it.route == backStackEntry.value?.destination?.route }
        ?: items[middleIndex]
    val selectedIndex = items.indexOf(selectedItem)

    val circleOffset = remember { Animatable(middleIndex.toFloat()) }
    val cutoutCenterOffset = remember { Animatable(middleIndex.toFloat()) }

    LaunchedEffect(selectedIndex) {
        async {
            circleOffset.animateTo(
                selectedIndex.toFloat(),
                animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
            )
        }
        async {
            cutoutCenterOffset.animateTo(
                selectedIndex.toFloat(),
                animationSpec = tween(durationMillis = 500 , easing = LinearOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
    ) {
        val horizontalPadding = 16.dp
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val tabWidth = (screenWidth - horizontalPadding * 2) / items.size
        val circleOffsetX = (circleOffset.value * tabWidth.value - tabWidth.value).dp

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp
            ),
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
            val density = LocalDensity.current.density
            val cutoutCenterPx = (tabWidth.value * density) * (cutoutCenterOffset.value + 0.625f)
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(
                        shape = BottomBarShape(cutoutCenter = cutoutCenterPx),
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

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(
                    x = circleOffsetX,
                    y = 0.dp
                )
                .size(60.dp * 1.2f)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            topbarlightblue,
                            topbardarkblue
                        )
                    )
                )
                .animateContentSize()
        ) {
            if (selectedIndex >= 0) {
                Icon(
                    imageVector = selectedItem.icon,
                    contentDescription = selectedItem.name,
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}
