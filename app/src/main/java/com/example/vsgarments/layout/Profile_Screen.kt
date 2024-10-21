package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.vsgarments.R
import com.example.vsgarments.fontBaloo
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

@Composable
fun Profile_Screen(
    modifier: Modifier
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(Color.White)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp,
                        bottom = 20.dp
                    ) ,
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
                    Box (
                        modifier = Modifier
                            .size(56.dp)
                            .clip(
                                shape = RoundedCornerShape(28.dp)
                            )
                            .background(Color.Transparent)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.waddle_dees),
                            contentDescription = "Your image description",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }
                }
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
            elevation = CardDefaults.cardElevation(10.dp) ,
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
                        contentDescription = "" ,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Profile",
                        fontSize = 23.sp ,
                        color = Color.Black,
                        fontFamily = fontBaloo ,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
        }
    }

}