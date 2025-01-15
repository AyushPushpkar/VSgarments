package com.example.vsgarments.view_functions

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.authentication.RegisterViewModel
import com.example.vsgarments.authentication.User
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue


@Composable
fun AppTopBar(
    navController : NavController ,
    context: Context
) {

    var userName by rememberSaveable {
        mutableStateOf("Anonymous")
    }
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val savedUserName = sharedPreferences.getString("username", null)

    if (savedUserName != null) {
        userName = savedUserName
    }

    val registerViewModel : RegisterViewModel = hiltViewModel()
    val currentUserResource by registerViewModel.currentUser.collectAsState()

    when (currentUserResource) {
        is Resource.Loading -> {
            //CircularProgressIndicator()
        }
        is Resource.Success -> {
            val user = (currentUserResource as Resource.Success<User>).data

            if (user != null) {
                // Update the displayed username only if it differs
                if (userName != user.userName) {
                    userName = user.userName
                    // Save the updated username to SharedPreferences
                    sharedPreferences.edit().putString("username", user.userName).apply()
                }
            }

        }
        is Resource.Error -> {
            // Show error message
            userName = "Anonymous"
        }
        else -> {
            // Handle unspecified state if necessary
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(135.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(
                    bottomStart = 15.dp,
                    bottomEnd = 15.dp
                ),
                clip = false,
                ambientColor = Color.Black,
                spotColor = Color.Black
            ),
        shape = RoundedCornerShape(
            bottomStart = 15.dp,
            bottomEnd = 15.dp
        ),
        colors = CardDefaults.cardColors(Color.Transparent),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            topbardarkblue,
                            topbarlightblue
                        )
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.topback),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                        vertical = 20.dp
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .clickable {
                            navController.navigate(Screen.Profile_Screen.route)

                        },
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Box(
                        modifier = Modifier
                            .height(36.dp)
                            .clip(
                                shape = RoundedCornerShape(18.dp)
                            )
                            .background(tintGreen)
                            .border(
                                width = 2.5f.dp,
                                color = Color(0xFFABE5FF),
                                shape = CircleShape
                            )
                            .padding(
                                start = 65.dp,
                                end = 12.dp
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = userName,
                            fontFamily = fontInter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = textcolorgrey ,
                            maxLines = 1 ,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(
                                shape = CircleShape
                            )
                            .border(
                                width = 2.5f.dp,
                                color = Color(0xFFABE5FF),
                                shape = CircleShape
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.waddle_dees),
                            contentDescription = "Your image description",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(40.dp)
                            .padding(top = 15.dp)
                            .background(color = Color.Transparent)

                    ) {
                        Image(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.Wishlist.route)

                                },
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "add account icon",
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(40.dp)
                            .padding(top = 13.dp)
                            .background(color = Color.Transparent)
                    ) {
                        Image(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.CartScreen.route)

                                },
                            painter = painterResource(id = R.drawable.bitcoin_icons_cart_filled),
                            contentDescription = "add account icon"
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(40.dp)
                            .padding(top = 15.dp)
                            .background(color = Color.Transparent)
                    ) {
                        Image(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.Profile_Screen.route)

                                },
                            painter = painterResource(id = R.drawable.bell),
                            contentDescription = "add account icon"
                        )
                    }
                }
            }
        }
    }
}