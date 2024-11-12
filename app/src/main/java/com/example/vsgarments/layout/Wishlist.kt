package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

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
                ) {
                }
                
                Spacer(modifier = Modifier.width(10.dp))
            }

            itemsIndexed(imageList.chunked(2)) { _, pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    pair.forEach { imageitem ->

                        Column(
                            modifier = Modifier
                                .background(color = Color.White)
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = imageitem.imageresId),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .width(180.dp)
                                    .height(300.dp)
                                    .padding(10.dp),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                text = imageitem.name,
                                color = textcolorblue
                            )
                            Text(
                                text = "${imageitem.rate}USD",
                                color = textcolorblue
                            )
                        }
                    }
                }
            }
        }
    }
}

val imageList = listOf(
    ImageList(
        R.drawable.bulk_order,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.bulk_order,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.test,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList(
        R.drawable.custom,
        300,
        "Aryan"
    ),

    )

data class ImageList(
    val imageresId: Int,
    val rate: Int,
    val name: String,
)

