package com.example.vsgarments.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.dataStates.ImageItem
import com.example.vsgarments.dataStates.imageList
import com.example.vsgarments.view_functions.SizeSelection

@Composable
fun DisplayScreen(
    modifier: Modifier,
    navController: NavController,
    imageItem: ImageItem?,
) {

    val updatedImageItem = remember { mutableStateOf(imageItem) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {
        val displayScrollview = rememberScrollState()
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(displayScrollview)
        ) {
            updatedImageItem.value?.let {item ->
                val selectedSize = remember { mutableStateOf(item.size ?: "") }
                val currentPrice = remember { mutableIntStateOf(item.currprice) }
                val originalPrice = remember { mutableIntStateOf(item.ogprice) }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    colors = CardDefaults.cardColors(appbackgroundcolor),
                    shape = RoundedCornerShape(
                        bottomStart = 25.dp,
                        bottomEnd = 25.dp
                    ),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.8f)
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 25.dp,
                                    bottomEnd = 25.dp
                                )
                            )
                            .background(
                                appbackgroundcolor
                            )
                            .padding(
                                start = 4.dp,
                                end = 4.dp
                            ),
                    ) {
                        Image(
                            painter = painterResource(id = item.imageresId),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clip(
                                    shape = RoundedCornerShape(
                                        topStart = 0.dp,
                                        topEnd = 0.dp,
                                        bottomStart = 25.dp,
                                        bottomEnd = 25.dp
                                    )
                                )

                        )

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.01f)
                            .background(appbackgroundcolor)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp , horizontal = 20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp) ,
                        text = item.CompanyName,
                        color = textcolorgrey,
                        fontSize = 20.sp ,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp) ,
                        text = item.name ,
                        color = textcolorgrey ,
                        fontSize = 18.sp
                    )
                    Row (
                        modifier = Modifier ,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            modifier = Modifier.padding( vertical = 2.dp),
                            text = "MRP ",
                            color = tintGrey ,
                        )
                        Text(
                            modifier = Modifier.padding( vertical = 2.dp),
                            text = "₹${originalPrice.value}",
                            color = tintGrey ,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp , vertical = 2.dp),
                            text = "₹${currentPrice.value}",
                            color = textcolorblue
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp) ,
                        text = "Select Size",
                        color = textcolorgrey,
                        fontSize = 20.sp ,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    SizeSelection(
                        imageItem = item ,
                        onSizeUpdated = {
                            updatedImageItem.value = it
                            currentPrice.value = it.currprice
                            originalPrice.value = it.ogprice
                            selectedSize.value = it.size ?: ""
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))

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
                                .weight(1f),
                            onClick = {
                                navController.navigate(Screen.CartScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = tintGreen
                            ),
                            shape = RoundedCornerShape(15.dp),
                            contentPadding = PaddingValues(
                                horizontal = 20.dp ,
                                vertical = 11.dp
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
                                        .size(17.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.edit_pen),
                                        contentDescription = "wishlist icon"
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Add to Cart ",
                                    fontSize = 16.sp,
                                )

                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            modifier = Modifier 
                                .weight(1f),
                            onClick = {
                                navController.navigate(Screen.Wishlist.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = tintGreen
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 20.dp,
                                vertical = 11.dp
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
                                        .size(17.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.edit_pen),
                                        contentDescription = "wishlist icon"
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Wishlist",
                                    fontSize = 16.sp,
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box (
                        modifier = Modifier
                            .height(500.dp)
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = tintGrey,
                                shape = RoundedCornerShape(15.dp)
                            ) ,
                    ){

                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
            Column(
                modifier = Modifier
                    .background(appbackgroundcolor)
                    .padding(
                        horizontal = 4.dp,
                        vertical = 5.dp
                    )
            ) {
                LazyRow(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(Color.White)
                        .padding(horizontal = 5.dp)
                ) {
                    itemsIndexed(imageList) { index, imageItem ->
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .width(150.dp)
                                .fillMaxHeight()
                                .padding(
                                    horizontal = 6.dp,
                                    vertical = 8.dp
                                )
                        ) {
                            Image(
                                painter = painterResource(id = imageItem.imageresId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {

                                    },
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp , vertical = 2.dp),
                                text = imageItem.name,
                                color = textcolorblue ,
                                maxLines = 1 ,
                                overflow = TextOverflow.Ellipsis ,
                                fontSize = 13.sp
                            )

                            Row (
                                modifier = Modifier ,
                                verticalAlignment = Alignment.CenterVertically
                            ){

                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp , vertical = 1.dp),
                                    text = "₹${imageItem.ogprice}",
                                    color = tintGrey ,
                                    textDecoration = TextDecoration.LineThrough ,
                                    fontSize = 12.sp
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp , vertical = 1.dp),
                                    text = "₹${imageItem.currprice}",
                                    color = textcolorblue ,
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            val percentless = percentLess(imageItem.ogprice , imageItem.currprice)
                            Box(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topEnd = 5.dp,
                                            bottomEnd = 5.dp
                                        )
                                    )
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                Color(0xFFF9FFFB),
                                                Color(0xFF09EE4B)
                                            )
                                        )
                                    )
                                    .padding(
                                        start = 45.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp , vertical = 1.dp),
                                    text = "${percentless}% off",
                                    color = Color.White ,
                                    fontSize = 13.sp ,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth()
                        .background(appbackgroundcolor)
                )
                LazyRow(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(Color.White)
                        .padding(horizontal = 5.dp)
                ) {
                    itemsIndexed(imageList) { index, imageItem ->
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .width(150.dp)
                                .fillMaxHeight()
                                .padding(
                                    horizontal = 6.dp,
                                    vertical = 8.dp
                                )
                        ) {
                            Image(
                                painter = painterResource(id = imageItem.imageresId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(230.dp)
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {

                                    },
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp , vertical = 2.dp),
                                text = imageItem.name,
                                color = textcolorblue ,
                                maxLines = 1 ,
                                overflow = TextOverflow.Ellipsis ,
                                fontSize = 13.sp
                            )

                            Row (
                                modifier = Modifier ,
                                verticalAlignment = Alignment.CenterVertically
                            ){

                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp , vertical = 1.dp),
                                    text = "${imageItem.ogprice}$",
                                    color = tintGrey ,
                                    textDecoration = TextDecoration.LineThrough ,
                                    fontSize = 12.sp
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp , vertical = 1.dp),
                                    text = "${imageItem.currprice}$",
                                    color = textcolorblue ,
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            val percentless = percentLess(imageItem.ogprice , imageItem.currprice)
                            Box(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topEnd = 5.dp,
                                            bottomEnd = 5.dp
                                        )
                                    )
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                Color(0xFFF9FFFB),
                                                Color(0xFF28FF65)
                                            )
                                        )
                                    )
                                    .padding(
                                        start = 45.dp,
                                        end = 5.dp
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp , vertical = 1.dp),
                                    text = "${percentless}% off",
                                    color = Color.White ,
                                    fontSize = 13.sp ,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
            Box (
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ){

            }
        }
    }
    Card(
        modifier = modifier
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
                if (imageItem != null) {
                    Text(
                        text = imageItem.CompanyName,
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