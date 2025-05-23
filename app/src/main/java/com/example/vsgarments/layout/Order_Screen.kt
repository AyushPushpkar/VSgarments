package com.example.vsgarments.layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.vsgarments.ui.theme.appbackgroundcolor


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.grey
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.AppTopBar

@Composable
fun Order_Screen(
    modifier: Modifier ,
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor),
    ) {

        Column {


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .offset(x = 10.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Spacer(modifier = Modifier
                                .height(135.dp)
                                .background(Color.White))

                            Text(
                                text = "My Order",
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                fontFamily = fontBaloo,
                                modifier = Modifier.background(color = Color.Transparent)
                            )
                            Text(
                                text = "SEARCH",
                                color = Color.Black,
                                modifier = Modifier.background(color = Color.Transparent)
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                            )

                          Orderbutton()

                        }
                    }
                }

            }
        }

        AppTopBar(navController = navController, context = LocalContext.current)
    }
}

@Composable
fun Orderbutton(){
    var selectedbutton by remember { mutableIntStateOf(0) }
    var selectedRow by remember { mutableStateOf("row1") }
    val buttonlabes1 = listOf("All","Shipped","Order","Return")
    val buttonlabes2 = listOf("Cancelled","Exchange","Delivered")
    fun hb0(){

    }
    fun hb1(){

    }
    fun hb2(){

    }
    fun hb3(){

    }
    fun hb4(){

    }
    fun hb5(){

    }
    fun hb6(){

    }

    Column {
        Row(modifier = Modifier
            .padding(end = 10.dp)){
            buttonlabes1.forEachIndexed{index, lable ->
             val isSelected = selectedRow == "row1" && selectedbutton == index
                val backgroundView = if(isSelected) tintGreen else appbackgroundcolor

                Button(
                    onClick = {
                        selectedbutton = index
                        selectedRow = "row1"
                        if (index != 0){
                            when (index){
                                1->hb1()
                                2->hb2()
                                3->hb3()
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(backgroundView),
                    border = if(isSelected)BorderStroke(1.dp, color = textcolorblue) else BorderStroke(1.dp, color = Color.LightGray),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = 3.dp)

                ) {
                    Text(
                        text = lable,
                        color = if(isSelected) textcolorblue else grey
                    )
                }

            }
        }

        Row(modifier = Modifier
            .padding(end =  10.dp)){
            buttonlabes2.forEachIndexed{index, lable ->
                val isSelected = selectedRow == "row2" && selectedbutton == index
                val backgroundView = if(isSelected) tintGreen else appbackgroundcolor

                Button(
                    onClick = {
                        selectedbutton = index
                        selectedRow = "row2"
                        when (index){
                            4->hb4()
                            5->hb5()
                            6->hb6()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundView),
                    border = if(isSelected)BorderStroke(1.dp, color = textcolorblue) else BorderStroke(1.dp, color = Color.LightGray),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(3.dp)
                ) {
                    Text(
                        text = lable,
                        color = if(isSelected) textcolorblue else grey
                    )
                }

            }
        }
    }
}





