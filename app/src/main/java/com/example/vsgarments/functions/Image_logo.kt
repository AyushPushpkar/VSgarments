package com.example.vsgarments.functions
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun Image_logo(
 painter: Painter,
 description:String,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)) {
        Image(painter = painter,
            contentDescription =description,
            contentScale = ContentScale.Fit)
    }
}