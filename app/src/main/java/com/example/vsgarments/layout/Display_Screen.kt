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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.dataStates.ImageItem
import com.example.vsgarments.dataStates.imageList
import com.example.vsgarments.view_functions.ExpandableText3
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
                .background(appbackgroundcolor)
                .fillMaxSize()
                .verticalScroll(displayScrollview)
        ) {
            updatedImageItem.value?.let { item ->
                val selectedSize = remember { mutableStateOf(item.size ?: "") }
                val currentPrice = remember { mutableIntStateOf(item.currprice) }
                val originalPrice = remember { mutableIntStateOf(item.ogprice) }

                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    Spacer(modifier = Modifier.height(70.dp))
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
                    Spacer(modifier = Modifier.height(15.dp))
                }

                Column(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        )
                        .background(Color.White)
                        .padding(
                            vertical = 10.dp,
                            horizontal = 20.dp
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = item.CompanyName,
                        color = textcolorgrey,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = item.name,
                        color = textcolorgrey,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    val percentLess by remember(
                        originalPrice.intValue,
                        currentPrice.intValue
                    ) {
                        derivedStateOf {
                            if (originalPrice.intValue > 0) {
                                ((originalPrice.intValue - currentPrice.intValue) * 100) / originalPrice.intValue
                            } else {
                                0
                            }
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 2.dp),
                                text = "MRP ",
                                color = tintGrey,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 2.dp),
                                text = "₹${originalPrice.intValue}",
                                color = tintGrey,
                                textDecoration = TextDecoration.LineThrough
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "₹${currentPrice.intValue}",
                                color = topbardarkblue,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 21.sp
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 200.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(
                                        object : Shape {
                                            override fun createOutline(
                                                size: Size,
                                                layoutDirection: LayoutDirection,
                                                density: Density,
                                            ): Outline {
                                                val path = Path().apply {
                                                    moveTo(
                                                        0f,
                                                        0f
                                                    )
                                                    lineTo(
                                                        0f,
                                                        size.height
                                                    )
                                                    lineTo(
                                                        size.width * 0.9f,
                                                        size.height
                                                    )
                                                    lineTo(
                                                        size.width,
                                                        0f
                                                    )
                                                    close()
                                                }
                                                return Outline.Generic(path)
                                            }
                                        }
                                    )
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                topbardarkblue,
                                                topbarlightblue
                                            )
                                        ),
                                    )
                                    .padding(
                                        start = 10.dp,
                                        end = 15.dp,
                                        top = 2.dp,
                                        bottom = 2.dp
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${percentLess}% off!",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    if (
                        item.sizeToStockMap.all { !it.value }
                    ) {
                        Text(
                            text = "Out of Stock !",
                            color = topbarlightblue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = "Select Size",
                        color = textcolorgrey,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    SizeSelection(
                        imageItem = item,
                        onSizeUpdated = {
                            updatedImageItem.value = it
                            currentPrice.intValue = it.currprice
                            originalPrice.intValue = it.ogprice
                            selectedSize.value = it.size ?: ""
                        }
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(
                                vertical = 25.dp,
                                horizontal = 0.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                navController.navigate(Screen.Wishlist.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0x66676767)
                            ),

                            contentPadding = PaddingValues()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                topbarlightblue,
                                                topbardarkblue
                                            )
                                        ),
                                        shape = RoundedCornerShape(0.dp)
                                    )
                                    .padding(
                                        horizontal = 17.dp,
                                        vertical = 11.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(17.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.edit_pen),
                                        contentDescription = "wishlist icon"
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Add to Cart",
                                    fontSize = 19.sp,
                                    color = Color.White,
                                    fontFamily = fontBaloo,
                                    fontWeight = FontWeight.SemiBold
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
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0x66676767)
                            ),
                            contentPadding = PaddingValues()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                topbarlightblue,
                                                topbardarkblue
                                            )
                                        ),
                                        shape = RoundedCornerShape(0.dp)
                                    )
                                    .padding(
                                        horizontal = 17.dp,
                                        vertical = 11.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.Center
                            ) {
                                Box(
                                    modifier = Modifier
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
                                    fontSize = 19.sp,
                                    color = Color.White,
                                    fontFamily = fontBaloo,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = tintGrey,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(15.dp),
                    ) {
                        Text(
                            text = "Product Details" ,
                            color = textcolorgrey ,
                            fontWeight = FontWeight.SemiBold ,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        item.productDetails.keys.forEach { attr ->
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = attr ,
                                    color = tintGrey
                                )
                                item.productDetails[attr]?.let {
                                    Text(
                                        text = it ,
                                        modifier = Modifier
                                            .weight(1f) ,
                                        color = textcolorgrey
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Details" ,
                            color = textcolorgrey ,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        
                        ExpandableText3(
                            item = item.description + item.description,
                            textColor = tintGrey
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            Column(
                modifier = Modifier
                    .background(appbackgroundcolor)
                    .padding(
                        horizontal = 5.dp,
                        vertical = 5.dp
                    )
            ) {
                Card(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(
                                5.dp
                            ),
                            clip = false,
                            ambientColor = Color.Black,
                            spotColor = Color.Black
                        ),
                    shape = RoundedCornerShape(
                        5.dp
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    LazyRow(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(5.dp)
                            )
                            .background(Color.White)
                            .padding(horizontal = 8.dp)
                    ) {
                        itemsIndexed(imageList) { index, imageItem ->
                            Column(
                                modifier = Modifier
                                    .background(Color.White)
                                    .width(160.dp)
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
                                        .padding(
                                            horizontal = 5.dp,
                                            vertical = 2.dp
                                        ),
                                    text = imageItem.name,
                                    color = textcolorgrey,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 5.dp,
                                            vertical = 1.dp
                                        ),
                                        text = "₹${imageItem.ogprice}",
                                        color = tintGrey,
                                        textDecoration = TextDecoration.LineThrough,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 5.dp,
                                            vertical = 1.dp
                                        ),
                                        text = "₹${imageItem.currprice}",
                                        color = textcolorblue,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                val percentless = percentLess(
                                    imageItem.ogprice,
                                    imageItem.currprice
                                )
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
                                                    topbarlightblue
                                                )
                                            )
                                        )
                                        .padding(
                                            start = 60.dp,
                                            end = 20.dp
                                        )
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 5.dp,
                                                vertical = 1.dp
                                            ),
                                        text = "${percentless}% off",
                                        color = Color.White,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )
                Card(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(
                                5.dp
                            ),
                            clip = false,
                            ambientColor = Color.Black,
                            spotColor = Color.Black
                        ),
                    shape = RoundedCornerShape(
                        5.dp
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                RoundedCornerShape(5.dp)
                            )
                            .background(Color.White)
                            .padding(horizontal = 8.dp)
                    ) {
                        itemsIndexed(imageList) { index, imageItem ->
                            Column(
                                modifier = Modifier
                                    .background(Color.White)
                                    .width(160.dp)
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
                                        .padding(
                                            horizontal = 5.dp,
                                            vertical = 2.dp
                                        ),
                                    text = imageItem.name,
                                    color = textcolorgrey,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 5.dp,
                                            vertical = 1.dp
                                        ),
                                        text = "₹${imageItem.ogprice}",
                                        color = tintGrey,
                                        textDecoration = TextDecoration.LineThrough,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 5.dp,
                                            vertical = 1.dp
                                        ),
                                        text = "₹${imageItem.currprice}",
                                        color = textcolorblue,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                val percentless = percentLess(
                                    imageItem.ogprice,
                                    imageItem.currprice
                                )
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
                                                    Color(0xFF36BFF5)
                                                )
                                            )
                                        )
                                        .padding(
                                            start = 60.dp,
                                            end = 20.dp
                                        )
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(
                                                horizontal = 5.dp,
                                                vertical = 1.dp
                                            ),
                                        text = "${percentless}% off",
                                        color = Color.White,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    )
                    .background(Color.White)
            ) {

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
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            navController.navigate(Screen.Profile_Screen.route)
                        }
                )
                Spacer(modifier = Modifier.width(30.dp))
                if (imageItem != null) {
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = imageItem.CompanyName,
                            fontSize = 21.sp,
                            color = Color.Black,
                            fontFamily = fontBaloo,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,

                            )
                    }
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
                        .height(50.dp)
                        .width(30.dp)
                        .padding(top = 13.dp)
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
                        .height(50.dp)
                        .width(30.dp)
                        .padding(top = 11.dp)
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
                        .height(50.dp)
                        .width(30.dp)
                        .padding(top = 13.dp)
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