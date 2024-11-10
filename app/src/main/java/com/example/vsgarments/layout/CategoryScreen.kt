package com.example.vsgarments.layout

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.lightblack
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.textdarkblue
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.AppTopBar


@Composable
fun CategoryScreen(
    navController: NavController ,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = appbackgroundcolor)
            ) {
                Spacer(modifier = Modifier.height(155.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Category",
                        fontFamily = fontBaloo,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textdarkblue
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .fillMaxHeight(.5f)
                    ) {
                        Box(
                            modifier = Modifier
                        ){

                            Image(
                                painter = painterResource(id = R.drawable.topback),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize() ,
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                lightblack,
                                                Color.Transparent
                                            )
                                        )
                                    )
                            )
                            Box(modifier = Modifier
                                .fillMaxHeight(.5f),
                                contentAlignment = Alignment.Center){
                                Text(
                                    text = "  Bulk \n\n  Order",
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = fontInter,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.7f)
                            .fillMaxHeight(.5f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.topback),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillHeight
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                lightblack,
                                                Color.Transparent
                                            )
                                        )
                                    )
                            )

                            Box(modifier = Modifier
                                .rotate(270f)
                                .fillMaxHeight(.5f)
                                .offset(y = (-10).dp),
                                contentAlignment = Alignment.CenterStart){
                                Text(
                                    text = "Retail" ,
                                    fontFamily = fontInter,
                                    fontSize = 35.sp ,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                            }
                        }

                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.90f)
                            .fillMaxHeight(.5f)
                    ) {


                        Box(modifier = Modifier){

                            Image(
                                painter = painterResource(id = R.drawable.topback),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                lightblack,
                                                Color.Transparent
                                            )
                                        )
                                    )
                            )

                            Box(modifier = Modifier
                                .padding(vertical = 20.dp, horizontal = 15.dp)){
                                Text(
                                    text = "Custom",
                                    fontFamily = fontInter,
                                    fontSize = 35.sp ,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )

                            }
                        }
                    }
                }

            }

        AppTopBar(navController = navController)


    }

}
