package com.example.vsgarments.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.vsgarments.R
import com.example.vsgarments.fontBaloo
import com.example.vsgarments.fontInter
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

@Composable
fun Profile_Screen(
    modifier: Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(235.dp)
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
                    ),
                verticalAlignment = Alignment.Bottom
            ) {

                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(66.dp)
                        .clip(
                            shape = RoundedCornerShape(33.dp)
                        )
                        .background(tintGreen)
                        .border(
                            shape = RoundedCornerShape(33.dp),
                            color = topbarlightblue,
                            width = 3.dp
                        )
                        .padding(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(
                                shape = RoundedCornerShape(28.dp)
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
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    )
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxHeight(0.45f),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = tintGreen
                    ),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 30.dp,
                        top = 10.dp,
                        bottom = 10.dp
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
                            text = "Order",
                            fontSize = 15.sp,
                        )

                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxHeight(0.45f),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = tintGreen
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 10.dp
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
                            text = "Wishlist",
                            fontSize = 15.sp,
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White),
            ) {

            }
            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White),
            ) {

            }
            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White),
            ) {

            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .background(Color.White),
            ) {

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .graphicsLayer {
                            clip = false
                            spotShadowColor = Color.Red
                            ambientShadowColor = Color.Red
                            shape = RoundedCornerShape(19.dp)
                            shadowElevation = 10f
                        },
                    colors = CardDefaults.cardColors(Color.Transparent),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFFF7F7))
                            .clickable(
                                indication = rememberRipple(color = Color(0xFFFFB3B3)), // Add ripple effect
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
                            color = Color(0xFFCE2828),
                            fontFamily = fontBaloo,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
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
                        top = 28.dp,
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