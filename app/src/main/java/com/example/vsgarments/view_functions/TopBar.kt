package com.example.vsgarments.view_functions

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

@Composable
fun AppTopBar(
    navController : NavController
) {
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
                                end = 10.dp
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Pavitr Prabhakar",
                            fontFamily = fontInter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = textcolorgrey
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
                    Spacer(modifier = Modifier.width(15.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(20.dp)
                            .padding(top = 20.dp)
                            .background(tintGreen)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.edit_pen),
                            contentDescription = "add account icon"
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(20.dp)
                            .padding(top = 20.dp)
                            .background(tintGreen)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.edit_pen),
                            contentDescription = "add account icon"
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(20.dp)
                            .padding(top = 20.dp)
                            .background(tintGreen)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.edit_pen),
                            contentDescription = "add account icon"
                        )
                    }
                }
            }
        }
    }
}