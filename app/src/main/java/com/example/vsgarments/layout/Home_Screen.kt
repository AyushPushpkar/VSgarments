package com.example.vsgarments.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.vsgarments.ui.theme.appbackgroundcolor

@Composable
fun HomeScreen(
    modifier: Modifier
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){}

}