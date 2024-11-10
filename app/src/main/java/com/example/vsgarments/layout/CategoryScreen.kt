package com.example.vsgarments.layout

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.R
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.lightblack
import com.example.vsgarments.ui.theme.textdarkblue


@Composable
fun CategoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
           .background(color = Color.Magenta)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(.2f)
                .fillMaxWidth()
                .background(color = Color.Transparent)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(.5f)
                        .offset(x = 25.dp)
                        .padding(horizontal = 10.dp)
                        .background(color = Color.Transparent, shape = RectangleShape),
                         contentAlignment = Alignment.CenterStart
                ){

                    Row(modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically){
                        Card(modifier = Modifier
                            .fillMaxHeight(.5f)
                            .offset(x = 40.dp)
                            .clip(shape = CircleShape)
                            .border(BorderStroke(0.dp, color = Color.Transparent), shape = CircleShape)) {
                            Box(modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 13.dp),
                                contentAlignment = Alignment.BottomCenter) {
                                Text(
                                    text = "Aryan Kumar",
                                    modifier = Modifier
                                        .padding(start = 25.dp)
                                )
                            }
                        }
                    }

                    Card(modifier = Modifier
                        .size(70.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color.Cyan)
                        .border(BorderStroke(2.5.dp, color = Color.Cyan), shape = CircleShape)){

                        Image(painter = painterResource(id = R.drawable.waddle_dees), contentDescription = "")
                    }

                }
                Row(modifier = Modifier
                    .fillMaxWidth()) {

                }
            }


        }
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topEnd = 15.dp , topStart = 15.dp)),
            shape = RoundedCornerShape(
                topEnd = 15.dp, topStart = 15.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = appbackgroundcolor)
            ) {
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .fillMaxHeight(.45537f)
                    ) {
                        Box(
                            modifier = Modifier
                        ){

                            Image(
                                painter = painterResource(id = R.drawable.bulk_order),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                lightblack,Color.Transparent
                                            )
                                        )
                                    )
                            )
                            Box(modifier = Modifier
                                .fillMaxHeight(.5f),
                                contentAlignment = Alignment.Center){
                                Text(
                                    text = "  Bulk \n  Order",
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
                            .fillMaxHeight(.45537f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hanger),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                lightblack,Color.Transparent
                                            )
                                        )
                                    )
                            )

                            Box(modifier = Modifier
                                .rotate(270f)
                                .fillMaxHeight(.5f)
                                .offset(y= (-10).dp),
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
                        .height(21.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.90f)
                            .fillMaxHeight(.4f)
                    ) {


                        Box(modifier = Modifier){

                            Image(
                                painter = painterResource(id = R.drawable.custom),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                lightblack,Color.Transparent
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
        }


    }

}
