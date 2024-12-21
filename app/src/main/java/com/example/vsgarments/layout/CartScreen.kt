package com.example.vsgarments.layout

import android.widget.Spinner
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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.rateboxGreen
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.Spinner
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.number_editText

@Composable
fun CartScreen(
    modifier: Modifier,
    navController: NavController,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {

        var initiallyOpened by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .background(appbackgroundcolor)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color.White)
                            .padding(
                                start = 20.dp,
                                end = 30.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(75.dp)
                                .background(appbackgroundcolor)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(75.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                            ) {
                                Text(
                                    text = "Deliver to : ",
                                    color = Color(0xFF6188A0),
                                    fontFamily = fontInter,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Address : cbjksdbsdc  sjbsdjcnsdaj jabcsddncalc sdjbbadjv ",
                                    color = Color(0xFF6188A0),
                                    fontFamily = fontInter,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Button(
                                    modifier = Modifier
                                        .width(90.dp),
                                    onClick = {
                                        navController.navigate(Screen.CartScreen.route)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = tintGreen
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    contentPadding = PaddingValues(
                                        horizontal = 20.dp,
                                        vertical = 3.dp
                                    ),
                                    border = BorderStroke(
                                        width = 2.dp,
                                        color = Color(0x66676767)
                                    )
                                ) {

                                    Text(
                                        text = "Change",
                                        color = tintGrey,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(appbackgroundcolor)
                    )
                }


                itemsIndexed(cartList) { _, item ->

                    Column(
                        modifier = Modifier
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                                .padding(
                                    top = 40.dp,
                                    start = 20.dp
                                )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = item.imageresId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(110.dp)
                                            .height(110.dp)
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            ),
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(modifier = Modifier.height(5.dp))

                                    val quantity = listOf(
                                        "1",
                                        "2",
                                        "3",
                                        "4",
                                        "5",
                                        "44"
                                    )
                                    var selectedqty by rememberSaveable {
                                        mutableStateOf("")
                                    }
                                    Spinner(
                                        modifier = Modifier,
                                        itemList = quantity,
                                        selectedItem = selectedqty,
                                        onItemSelected = { selectedqty = it },
                                        spinnerwidth = 110.dp
                                    )

                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 5.dp,
                                                vertical = 2.dp
                                            ),
                                        text = item.name,
                                        color = textcolorblue,
                                        maxLines = 1
                                    )

                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                            painter = painterResource(id = R.drawable.rounded_arrow_downward_24),
                                            contentDescription = ""
                                        )
                                        Spacer(modifier = Modifier.width(1.dp))
                                        val percentless = percentLess(
                                            item.ogprice,
                                            item.currprice
                                        )
                                        Text(
                                            modifier = Modifier.padding(vertical = 2.dp),
                                            text = "${percentless}%",
                                            color = rateboxGreen
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            modifier = Modifier.padding(
                                                horizontal = 5.dp,
                                                vertical = 2.dp
                                            ),
                                            text = "${item.ogprice}$",
                                            color = tintGrey,
                                            textDecoration = TextDecoration.LineThrough
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(
                                            modifier = Modifier.padding(
                                                horizontal = 5.dp,
                                                vertical = 2.dp
                                            ),
                                            text = "${item.currprice}$",
                                            color = textcolorblue
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .width(65.dp)
                                            .height(25.dp)
                                            .padding(horizontal = 5.dp)
                                            .clip(
                                                RoundedCornerShape(15.dp)
                                            )
                                            .background(rateboxGreen),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(horizontal = 7.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = item.rating.toString(),
                                                color = Color.White,
                                                fontFamily = fontBaloo,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 15.sp
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Image(
                                                modifier = Modifier.align(Alignment.CenterVertically),
                                                painter = painterResource(id = R.drawable.rounded_star_half_24),
                                                contentDescription = "",
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))

                            Row {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(0.6f)
                                        .padding(
                                            horizontal = 5.dp,
                                            vertical = 2.dp
                                        ),
                                    text = "Delivery by Jan 12 , Fri ",
                                    color = tintGrey,
                                    maxLines = 1
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(0.6f)
                                        .padding(
                                            horizontal = 5.dp,
                                            vertical = 2.dp
                                        ),
                                    text = "FREE",
                                    color = topbardarkblue,
                                    maxLines = 1
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        Row {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .weight(1f)
                                    .drawBehind {
                                        drawLine(
                                            color = appbackgroundcolor,
                                            start = Offset(
                                                0f,
                                                0f
                                            ),
                                            end = Offset(
                                                0f,
                                                size.height
                                            ),
                                            strokeWidth = 6f
                                        )
                                        drawLine(
                                            color = appbackgroundcolor,
                                            start = Offset(
                                                0f,
                                                size.height
                                            ),
                                            end = Offset(
                                                size.width,
                                                size.height
                                            ),
                                            strokeWidth = 6f
                                        )
                                        drawLine(
                                            color = appbackgroundcolor,
                                            start = Offset(
                                                size.width,
                                                0f
                                            ),
                                            end = Offset(
                                                size.width,
                                                size.height - 15f
                                            ),
                                            strokeWidth = 6f
                                        )
                                        drawLine(
                                            color = appbackgroundcolor,
                                            end = Offset(
                                                0f,
                                                0f
                                            ),
                                            start = Offset(
                                                size.width,
                                                0f
                                            ),
                                            strokeWidth = 6f
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .background(Color.White)
                                            .size(20.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.rounded_delete_24),
                                            contentDescription = "wishlist icon"
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        text = "Remove",
                                        color = tintGrey
                                    )

                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .weight(1f)
                                    .drawBehind {
                                        drawLine(
                                            color = appbackgroundcolor,
                                            start = Offset(
                                                0f,
                                                0f
                                            ),
                                            end = Offset(
                                                0f,
                                                size.height - 15f
                                            ),
                                            strokeWidth = 6f
                                        )
                                        drawLine(
                                            color = appbackgroundcolor,
                                            start = Offset(
                                                0f,
                                                size.height
                                            ),
                                            end = Offset(
                                                size.width,
                                                size.height
                                            ),
                                            strokeWidth = 6f
                                        )
                                        drawLine(
                                            color = appbackgroundcolor,
                                            start = Offset(
                                                size.width,
                                                0f
                                            ),
                                            end = Offset(
                                                size.width,
                                                size.height
                                            ),
                                            strokeWidth = 6f
                                        )
                                        drawLine(
                                            color = appbackgroundcolor,
                                            end = Offset(
                                                0f,
                                                0f
                                            ),
                                            start = Offset(
                                                size.width,
                                                0f
                                            ),
                                            strokeWidth = 6f
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .background(Color.White)
                                            .size(20.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                                            contentDescription = "wishlist icon"
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        text = "Buy Now",
                                        color = tintGrey
                                    )

                                }
                            }
                        }


                        Box(
                            modifier = Modifier
                                .height(3.dp)
                                .fillMaxWidth()
                                .background(appbackgroundcolor)
                        )
                    }

                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(appbackgroundcolor)
                            .padding(
                                start = 20.dp,
                                end = 30.dp
                            )
                    )
                }

            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(appbackgroundcolor)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(
                                start = 23.dp,
                                end = 23.dp,
                                bottom = 2.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        var totalogprice = 0
                        var totalcurrprice = 0
                        cartList.forEach {
                            totalogprice += it.ogprice
                            totalcurrprice += it.currprice
                        }

                        Column() {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "${totalogprice}$",
                                color = tintGrey,
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 12.sp
                            )
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "${totalcurrprice}$",
                                color = textcolorblue,
                                fontSize = 20.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            blue_Button(
                                width_fraction = 0.5f,
                                button_text = "Place Order",
                                font_Family = fontBaloo
                            ) {

                            }
                        }
                    }

                }
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
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.navigate(Screen.MainScreen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "My Cart",
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

private val cartList = listOf(
    CartList(R.drawable.bulk_order, 300, 400 ,"Aryan" , 4.0f ),
    CartList(R.drawable.test , 300 , 400 ,"Aryan" , 4.5f) ,
    CartList(R.drawable.custom, 300, 400 ,"Aryan" , 3.5f),
    CartList(R.drawable.test , 300 , 400 ,"Aryan" , 4.5f) ,
    CartList(R.drawable.custom, 300, 400 ,"Aryan" , 3.5f),
)

private data class CartList(
    val imageresId: Int,
    val currprice: Int,
    val ogprice: Int,
    val name: String,
    val rating: Float,
)

@Composable
fun Address_dialog(
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
                        font_Family = fontBaloo ,
                        onClick = {
                            navController.navigate(Screen.Login_Screen.route)
                        }
                    )
                }

            }
        }
    }
}