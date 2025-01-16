package com.example.vsgarments.layout

import android.content.Context
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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.AppTopBar
import com.example.vsgarments.dataStates.ImageItem
import com.example.vsgarments.dataStates.imageList
import com.google.gson.Gson
import java.net.URLEncoder

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

            val context = LocalContext.current


            AppTopBar(navController = navController , context = context)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(imageList.chunked(2)) { _, pair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        pair.forEach { imageitem ->
                            val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp
                            Column(
                                modifier = Modifier
                                    .width(screenwidth / 2)
                                    .padding(10.dp)
                            ) {
                                val imageItemJson = Gson().toJson(imageitem)
                                val encodedImageItem = URLEncoder.encode(imageItemJson, "UTF-8")

                                Image(
                                    painter = painterResource(id = imageitem.imageresId),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterHorizontally)
                                        .fillMaxWidth()
                                        .height(278.dp)
                                        .clip(shape = RoundedCornerShape(10))
                                        .clickable {
                                            navController.navigate("${Screen.DisplayScreen.route}/$encodedImageItem")
                                        }

                                )
                                Row {
                                    Column {
                                        Text(
                                            text = imageitem.CompanyName,
                                            color = lightblack,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            fontFamily = fontInter
                                        )
                                        Text(
                                            text = imageitem.name,
                                            color = grey,
                                            fontSize = 14.sp,
                                            fontFamily = fontInter,
                                            maxLines = 1,
                                            modifier = Modifier
                                                .offset(y = -(5).dp)
                                        )

                                        Text(
                                            text = "₹${imageitem.currprice}",
                                            color = lightblack,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            fontFamily = fontInter,
                                            maxLines = 1,
                                            modifier = Modifier
                                                .offset(y = -(7).dp)
                                        )
                                    }
                                    HeartCheckBox(
                                        context = context,
                                        uid = imageitem.name
                                    )
                                }
                            }
                        }
                    }

                }

                item {
                    Spacer(modifier = Modifier
                        .height(75.dp)
                        .background(appbackgroundcolor))
                }
            }
        }
    }
}

//Shared Preferences
fun saveLikeButtonState(context: Context,isLiked: Boolean,uid:String){
    val sharedPreferences = context.getSharedPreferences("likeButtonPref",Context.MODE_PRIVATE)
    sharedPreferences.edit()
        .putBoolean("isLiked_$uid",isLiked).apply()
}

fun getLikeButtonState(context: Context,uid: String):Boolean{
    val sharedPreferences = context.getSharedPreferences("likeButtonPref",Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isLiked_$uid",false)
}

@Composable
fun HeartCheckBox(context: Context,uid: String){
    var isLiked by remember {
        mutableStateOf(false)
    }

    isLiked = getLikeButtonState(context,uid)

    val state by animateFloatAsState(
        targetValue = if(isLiked) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )

    val color = if(isLiked) topbardarkblue else Color.Gray

    Column(modifier = Modifier
        .clickable {
            isLiked = !isLiked
            saveLikeButtonState(
                context,
                isLiked,
                uid
            )
        }
        .scale(state)) {
        Image(
            painter = painterResource(R.drawable.heart),
            contentDescription = "Heart",
            colorFilter = ColorFilter.tint(color)
        )
    }
}


