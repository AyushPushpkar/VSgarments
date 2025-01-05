package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
import com.example.vsgarments.view_functions.blue_Button
import com.google.gson.Gson
import java.net.URLEncoder
import com.example.vsgarments.dataStates.imageList

@Composable
fun Wishlist(
    modifier: Modifier,
    navController: NavController,
) {
    val scrollview = rememberScrollState()
    Column(
        modifier = modifier
            .background(appbackgroundcolor)
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
                                navController.navigate(Screen.Profile_Screen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Wishlist",
                        fontSize = 23.sp,
                        color = Color.Black,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .background(tintGreen)
                        .padding(
                            start = 20.dp,
                            end = 30.dp
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    var isChecked by remember { mutableStateOf(true) }
                    Text(
                        text = "Show in stock products only",
                        color = Color(0xFF6188A0),
                        fontFamily = fontInter,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    ) {
                        Switch(
                            modifier = Modifier
                                .scale(0.8f),
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            colors = SwitchColors(
                                checkedThumbColor = tintGreen,
                                checkedTrackColor = topbardarkblue,
                                checkedBorderColor = Color.Transparent,
                                checkedIconColor = Color.Red,
                                uncheckedThumbColor = textcolorgrey,
                                uncheckedTrackColor = topbardarkblue,
                                uncheckedBorderColor = Color.Transparent,
                                uncheckedIconColor = Color.Black,
                                disabledCheckedThumbColor = Color.Black,
                                disabledCheckedTrackColor = Color.Black,
                                disabledCheckedBorderColor = Color.Black,
                                disabledCheckedIconColor = Color.Black,
                                disabledUncheckedThumbColor = Color.Black,
                                disabledUncheckedTrackColor = Color.Black,
                                disabledUncheckedBorderColor = Color.Black,
                                disabledUncheckedIconColor = Color.Black
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
            }

            itemsIndexed(imageList.chunked(2)) { _, pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(appbackgroundcolor)
                        .padding(vertical = 1.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    pair.forEach { imageitem ->
                        val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp

                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .width(screenwidth / 2)
                                .padding(10.dp)
                        ) {
                            val imageItemJson = Gson().toJson(imageitem)
                            val encodedImageItem = URLEncoder.encode(imageItemJson, "UTF-8")

                            Image(
                                painter = painterResource(id = imageitem.imageresId),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        navController.navigate("${Screen.DisplayScreen.route}/$encodedImageItem")
                                    },
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp , vertical = 2.dp),
                                text = imageitem.name,
                                color = tintGrey ,
                                maxLines = 1 ,
                                fontWeight = FontWeight.SemiBold
                            )

                            Row (
                                modifier = Modifier ,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    painter = painterResource(id = R.drawable.rounded_arrow_downward_24),
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.width(1.dp))
                                val percentless = percentLess(imageitem.ogprice , imageitem.currprice)
                                Text(
                                    modifier = Modifier.padding( vertical = 2.dp),
                                    text = "${percentless}%",
                                    color = rateboxGreen ,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp , vertical = 2.dp),
                                    text = "₹${imageitem.ogprice}",
                                    color = tintGrey ,
                                    textDecoration = TextDecoration.LineThrough
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp , vertical = 2.dp),
                                    text = "₹${imageitem.currprice}",
                                    color = textcolorblue ,
                                    fontWeight = FontWeight.Medium
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
                                    .background(rateboxGreen) ,
                                contentAlignment = Alignment.Center
                            ) {
                                Row (
                                    modifier = Modifier
                                        .padding(horizontal = 7.dp) ,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(
                                        text = imageitem.rating.toString(),
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

                            Box (
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(
                                        top = 15.dp,
                                        bottom = 5.dp
                                    )
                            ){
                                blue_Button(
                                    width_fraction = 0.8f,
                                    button_text = "Add to Cart" ,
                                    font_Family = fontBaloo
                                ) {

                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

fun percentLess(
    ogprice: Int,
    currprice: Int
): Int {
    val percentage = ((ogprice - currprice) * 100 / ogprice).toInt()
    return percentage
}


