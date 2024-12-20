package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.view_functions.AppTopBar

@Composable
fun MainScreen( navController: NavController) {

    AppTopBar(navController = navController)
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
       itemsIndexed(imageItem.chunked(2)){ _, pair->
           Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
               pair.forEach{ imageitem->
                   Column {
                       Image(painter = painterResource(id = imageitem.imageresId1), contentDescription = "")
                       Row {

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
        "Aryan"
    ),
    ImageList1(
        R.drawable.bulk_order,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.test,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),
    ImageList1(
        R.drawable.custom,
        300,
        "Aryan"
    ),

    )
private data class ImageList1(
    val imageresId1: Int,
    val rate: Int,
    val name: String,
)


