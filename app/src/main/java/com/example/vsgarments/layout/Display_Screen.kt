package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vsgarments.view_functions.ImageItem

@Composable
fun DisplayScreen(
    modifier: Modifier,
    navController: NavController,
    imageItem: ImageItem?
) {
    Column(modifier = modifier) {
        imageItem?.let {
            Image(
                painter = painterResource(id = imageItem.imageresId),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(278.dp)
                    .clip(shape = RoundedCornerShape(10))

            )
            Text(text = "Image Name: ${it.name}")
            Text(text = "Image Company: ${it.CompanyName}")
            Text(text = "Current Price: \$${it.currprice}")
            Text(text = "Original Price: \$${it.ogprice}")
            Text(text = "Rating: ${it.rating}")
        }
    }
}
