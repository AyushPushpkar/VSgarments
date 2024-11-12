package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen

@Composable
fun Wishlist(
    imageList: List<ImageList>,
    modifier: Modifier,
    navController: NavController,
){
    val scrollview = rememberScrollState()
    Column(modifier = modifier){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(color = Color.Cyan)){

        }

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            ){
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(color = Color.Blue)){
                }
            }

            itemsIndexed(imageList.chunked(2)){
                _,pair -> Row(modifier = Modifier.fillMaxWidth()) {
                    pair.forEach{
                     imageitem -> Column(modifier = Modifier){
                        Image( painter = painterResource(id = imageitem.imageresId),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .height(100.dp)
                                .padding(10.dp),
                            contentScale = ContentScale.Crop )

                        Text(
                            text = imageitem.name
                        )
                        Text(
                            text = "${imageitem.rate}USD"
                        )
                    }
                    }
            }
            }
        }
    }
}

val imageList = listOf(
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.bulk_order,300,"Aryan"),
    ImageList(R.drawable.test,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan"),
    ImageList(R.drawable.hanger,300,"Aryan")
)

data class ImageList(
    val imageresId : Int,
    val rate : Int,
    val name : String
)

