package com.example.vsgarments.view_functions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(){
    Box(
        modifier = Modifier
            .height(143.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        var query by remember { mutableStateOf("") }
        var isActive by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(
                        23.dp
                    ),
                    clip = true,
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                ),
            shape = RoundedCornerShape(23.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(10.dp),
        ) {
            SearchBar(
                query = query,
                onQueryChange = {
                    query = it
                },
                onSearch = {},
                active = isActive,
                onActiveChange = {
                    isActive = it
                } ,
                placeholder = { Text(text = "Search...") },
            ) {

            }
        }
    }
}