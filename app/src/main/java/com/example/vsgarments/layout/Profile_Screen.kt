package com.example.vsgarments.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.char_editText
import com.example.vsgarments.view_functions.number_editText

@Composable
fun Profile_Screen(
    navController: NavController ,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {
        var initiallyOpened by remember {
            mutableStateOf(false)
        }
        val profilescroll = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (!initiallyOpened) {
                        Modifier
                            .verticalScroll(profilescroll)
                    } else Modifier
                )
        ) {
            Box(
                modifier = Modifier
                    .height(75.dp)
                    .fillMaxWidth()
            )
            val accountscroll = rememberScrollState()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .background(Color.White)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp,
                        bottom = 20.dp
                    )
                    .horizontalScroll(accountscroll),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .clip(
                            shape = RoundedCornerShape(28.dp)
                        )
                        .background(tintGreen)
                        .border(
                            shape = RoundedCornerShape(28.dp),
                            color = topbarlightblue,
                            width = 3.dp
                        )
                        .padding(5.dp)
                        .clickable {
                            navController.navigate(Screen.EditProfile_Screen.route)

                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(
                                shape = RoundedCornerShape(23.dp)
                            )
                            .background(Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.waddle_dees),
                            contentDescription = "Your image description",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Hey ! Pavitr  ",
                        fontFamily = fontInter,
                        fontWeight = FontWeight.SemiBold,
                        color = textcolorgrey
                    )
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(
                            shape = RoundedCornerShape(28.dp)
                        )
                        .background(tintGreen)
                        .border(
                            shape = RoundedCornerShape(32.dp),
                            color = topbarlightblue,
                            width = 3.dp
                        )
                        .padding(5.dp)
                        .clickable {
                            initiallyOpened = true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(tintGreen)
                            .size(18.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.edit_pen),
                            contentDescription = "add account icon"
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    )
                    .background(Color.White)
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .width(130.dp),
                    onClick = {
                        navController.navigate(Screen.Wishlist.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = tintGreen
                    ),
                    shape = RoundedCornerShape(15.dp),
                    contentPadding = PaddingValues(
                        horizontal = 20.dp ,
                        vertical = 12.dp
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(0x66676767)
                    )
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Box(
                            modifier = Modifier
                                .background(tintGreen)
                                .size(18.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.edit_pen),
                                contentDescription = "wishlist icon"
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "My Cart ",
                            fontSize = 17.sp,
                        )

                    }
                }
                Button(
                    modifier = Modifier ,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = tintGreen
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(0x66676767)
                    )
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .background(tintGreen)
                                .size(18.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.edit_pen),
                                contentDescription = "wishlist icon"
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Wishlist",
                            fontSize = 17.sp,
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(7.dp))

            Help_address(
                icon = painterResource(id = R.drawable.edit_pen),
                icon_des = "Help icon",
                text = "Help" ,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(2.dp))

            Help_address(
                icon = painterResource(id = R.drawable.edit_pen),
                icon_des = "address icon",
                text = "Address"  ,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(2.dp))

            Help_address(
                icon = painterResource(id = R.drawable.edit_pen),
                icon_des = "Settings icon",
                text = "Settings" ,
                onClick = {
                    navController.navigate(Screen.Settings_Screen.route)
                }
            )
            Spacer(modifier = Modifier.height(7.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .background(Color.White)
                    .padding(
                        vertical = 25.dp,
                        horizontal = 30.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .clickable { },
                    text = "FAQs",
                    fontSize = 20.sp,
                    color = Color(0xFF6188A0),
                    fontFamily = fontBaloo,
                )
                Text(
                    modifier = Modifier
                        .clickable { },
                    text = "About Us",
                    fontSize = 20.sp,
                    color = Color(0xFF6188A0),
                    fontFamily = fontBaloo,
                )
                Text(
                    modifier = Modifier
                        .clickable { },
                    text = "Terms of Use",
                    fontSize = 20.sp,
                    color = Color(0xFF6188A0),
                    fontFamily = fontBaloo,
                )
                Text(
                    modifier = Modifier
                        .clickable { },
                    text = "Privacy Policy",
                    fontSize = 20.sp,
                    color = Color(0xFF6188A0),
                    fontFamily = fontBaloo,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 35.dp,
                        vertical = 25.dp
                    )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .graphicsLayer {
                            clip = false
                            spotShadowColor = Color.Red
                            ambientShadowColor = Color.Red
                            shape = RoundedCornerShape(20.dp)
                            shadowElevation = 10f
                        },
                    colors = CardDefaults.cardColors(Color.Transparent),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFFEEEE))
                            .clickable(
                                indication = rememberRipple(color = Color(0xFFFF7A7A)), // Add ripple effect
                                interactionSource = remember { MutableInteractionSource() }
                            ) {}
                            .border(
                                color = Color(0xFFCECECE),
                                width = 1.dp,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Logout",
                            fontSize = 25.sp,
                            color = Color(0xFFFF7C7C),
                            fontFamily = fontBaloo,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(70.dp))

            }
        }

        Login_dialog(
            navController = navController,
            initiallyOpened = initiallyOpened,
            onDismissRequest = { initiallyOpened = false }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    clip = false,
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                ),
            shape = RoundedCornerShape(
                bottomStart = 5.dp,
                bottomEnd = 5.dp
            ),
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
                    .padding(
                        start = 30.dp
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                            .clickable {
                                navController.navigate(Screen.MainScreen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Profile",
                        fontSize = 23.sp,
                        color = Color.Black,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
        }
    }

}

@Composable
fun Help_address(
    icon: Painter,
    icon_des: String,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                horizontal = 30.dp,
                vertical = 21.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .size(15.dp)
            ) {
                Image(
                    painter = icon,
                    contentDescription = icon_des
                )
            }
            Spacer(modifier = Modifier.width(25.dp))

            Text(
                text = text,
                fontFamily = fontInter,
                fontWeight = FontWeight.W600,
                fontSize = 18.sp,
                color = textcolorgrey
            )
        }
        Box(
            modifier = Modifier
                .background(Color.White)
                .size(16.dp)
        ) {
            Image(
                modifier = Modifier.clickable(onClick = onClick) ,
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "enter icon" ,
                colorFilter = ColorFilter.lighting(add = textcolorgrey , multiply = textcolorgrey)
            )
        }
    }
}

@Composable
fun Login_dialog(
    navController: NavController,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean,
    onDismissRequest: () -> Unit,
) {

    AnimatedVisibility(
        visible = initiallyOpened,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x4DB6E9FF))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(213.dp)
                    .background(Color.Transparent)
                    .clickable { onDismissRequest() }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .background(Color.White)
                    .padding(35.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {}  // Consume taps
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { _, _ -> /* Consume drag gestures */ }
                    },
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .height(0.dp)
                )

                Row {
                    Text(
                        modifier = Modifier.weight(9f),
                        text = "Log in for best experience ",
                        color = textcolorgrey,
                        fontSize = 16.sp,
                        fontFamily = fontInter,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onDismissRequest()
                            },
                        painter = painterResource(id = R.drawable.edit_pen),
                        contentDescription = "cross icon"
                    )
                }

                Text(
                    text = "Enter your phone number to continue",
                    color = textcolorgrey,
                    fontSize = 12.sp,
                    fontFamily = fontInter,
                    fontWeight = FontWeight.Medium
                )

                number_editText(
                    hint = "Mobile Number",
                    char_no = 10,
                    font_Family = fontInter
                )

                Column {
                    Row {
                        Text(
                            text = "By continuing, you agree to our",
                            color = textcolorgrey,
                            fontSize = 12.sp,
                            fontFamily = fontInter,
                            fontWeight = FontWeight.Normal,
                        )
                        Text(
                            text = " Terms of Use",
                            color = topbardarkblue,
                            fontSize = 12.sp,
                            fontFamily = fontInter,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .clickable {
                                }
                        )
                    }
                    Row {
                        Text(
                            text = "& ",
                            color = textcolorgrey,
                            fontSize = 12.sp,
                            fontFamily = fontInter,
                            fontWeight = FontWeight.Normal,
                        )
                        Text(
                            text = "Privacy Policy",
                            color = topbardarkblue,
                            fontSize = 12.sp,
                            fontFamily = fontInter,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .clickable {
                                }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(0.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    blue_Button(
                        modifier = Modifier,
                        width_fraction = 0.5f,
                        button_text = "Continue",
                        font_Family = fontInter ,
                        onClick = {
                            navController.navigate(Screen.Login_Screen.route)
                        }
                    )
                }

            }
        }
    }
}