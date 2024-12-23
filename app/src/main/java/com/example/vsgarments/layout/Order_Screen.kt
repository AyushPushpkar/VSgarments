package com.example.vsgarments.layout
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.vsgarments.ui.theme.appbackgroundcolor


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.AppTopBar
import com.example.vsgarments.view_functions.blue_Button

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


            AppTopBar(navController = navController)
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
                            Text(
                                text = "My Order",
                                color = Color.Black,
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
                            /*
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                order("All")
                                order("Shipped")
                                order("Order")
                                order("Delivered")
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(10.dp)
                            )
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 15.dp, bottom = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                order("Cancelled")
                                order("Exchange")
                                order("Return")
                                Text("    ")
                            }

 */
                          Orderbutton()

                        }
                    }
                }
            }
        }

    }
}

/*
*
*
@Composable
fun order(name:String,modifier: Modifier = Modifier){

    var isChecked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if(isChecked) 1.5f else 1f,
        animationSpec = spring(dampingRatio = Spring.StiffnessMedium)
    )
    val tintColor = if(isChecked)Color.Blue else Color.Gray

    Column(modifier = Modifier
        .scale(scale)
        .clickable { isChecked = !isChecked }) {
        Box(modifier = Modifier
            .background(color = tintColor, shape = CircleShape)
            .padding(horizontal = 10.dp, vertical = 2.dp)) {
            Text(
                text = name
            )
        }

    }
}

 */

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
                val backgroundView = if(isSelected) Color.Cyan else Color.LightGray

                Button(
                    onClick = {
                        selectedbutton = index
                        selectedRow = "row1"
                        if (index != 0){
                            when (index){
                                0->hb0()
                                1->hb1()
                                2->hb2()
                                3->hb3()
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(backgroundView),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = 3.dp)

                ) {
                    Text(
                        text = lable
                    )
                }

            }
        }

        Row(modifier = Modifier
            .padding(end =  10.dp)){
            buttonlabes2.forEachIndexed{index, lable ->
                val isSelected = selectedRow == "row2" && selectedbutton == index
                val backgroundView = if(isSelected) Color.Cyan else Color.LightGray

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
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(3.dp)
                ) {
                    Text(
                        text = lable
                    )
                }

            }
        }
    }
}





