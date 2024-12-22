package com.example.vsgarments.layout

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.grey
import com.example.vsgarments.ui.theme.lightblack
import com.example.vsgarments.view_functions.AppTopBar

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {
        Column {


            AppTopBar(navController = navController)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                    itemsIndexed(imageItem.chunked(2)) { _, pair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            pair.forEach { imageitem ->
                                val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp
                                Column(modifier = Modifier
                                    .width(screenwidth/2)
                                    .padding(10.dp)) {

                                    Image(
                                        painter = painterResource(id = imageitem.imageresId1),
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterHorizontally)
                                            .fillMaxWidth()
                                            .height(278.dp)
                                            .clip(shape = RoundedCornerShape(10))

                                    )
                                    Row {
                                        Column {
                                            Text(
                                                text = imageitem.Cname,
                                                color = lightblack,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                fontFamily = fontInter
                                            )
                                            Text(text = "Aryan",
                                                color = grey,
                                                fontSize = 14.sp,
                                                fontFamily = fontInter,
                                                maxLines = 1,
                                                modifier = Modifier
                                                    .offset(y = -(5).dp))

                                            Text(text = "${imageitem.rate}",
                                                color = lightblack,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                fontFamily = fontInter,
                                                maxLines = 1,
                                                modifier = Modifier
                                                    .offset(y = -(7).dp))
                                        }
                                        //Image(painter = painterResource(id = R.drawable.heart), contentDescription = "")
                                        Heartcheckbox()
                                    }
                                }
                            }
                        }

                }
            }
        }
    }
        }



private val imageItem = listOf(
    ImageList1(
        R.drawable.bulk_order,
        300,
        "Aryan",
        "The VS Garments"
    ),
    ImageList1(
        R.drawable.bulk_order,
        300,
        "Aryan",
        "The VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "The VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"

    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "The VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "The VS Garments"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan",
        "The VS Garments"
    ),

    )
private data class ImageList1(
    val imageresId1: Int,
    val rate: Int,
    val name: String,
    val Cname:String
)

@Composable
fun Heartcheckbox(modifier :Modifier = Modifier){
    var ischecked by remember { mutableStateOf(false) }

    val satate by animateFloatAsState(
        targetValue = if(ischecked) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    val tintcolor = if(ischecked) Color.Red else Color.Gray

    Column(modifier = Modifier
        .clickable { ischecked = !ischecked }
        .scale(satate)) {
         Image(painter = painterResource(R.drawable.heart),
             contentDescription = "Heart",
             colorFilter = ColorFilter.tint(tintcolor))
    }

}


