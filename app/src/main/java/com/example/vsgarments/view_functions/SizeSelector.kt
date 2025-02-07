package com.example.vsgarments.view_functions

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.vsgarments.dataStates.ProductItem
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey

@Composable
fun SizeSelection(
    imageItem: ProductItem,
    onSizeUpdated: (ProductItem) -> Unit
) {
    val predefinedSizeOrder = listOf("XS", "S", "M", "L", "XL", "XXL" , "Free")
    val availableSizes = imageItem.sizeToPriceMap.keys.sortedWith(compareBy { predefinedSizeOrder.indexOf(it) })
    val calSize =  imageItem.size ?: imageItem.sizeToPriceMap
        .entries
        .find { it.value.currentPrice == imageItem.currprice }
        ?.key ?: "S"
    var selectedSize by remember { mutableStateOf(calSize) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val sizerowscroll = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(sizerowscroll),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            availableSizes.forEach { clothSize ->
                val isSelected = selectedSize == clothSize
                val backgroundColor = if (isSelected) appbackgroundcolor else Color.White

                if (imageItem.sizeToStockMap[clothSize] == true)
                Button(
                    onClick = {
                        selectedSize = clothSize
                        imageItem.size = clothSize
                        imageItem.updatePriceBasedOnSize()
                        onSizeUpdated(imageItem)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            color = tintGrey,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    val price = imageItem.sizeToPriceMap[clothSize]
                    val currPrice = price?.currentPrice
                    Column {
                        Text(text = clothSize)
//                        Text(text = "₹$currPrice")
                    }
                }
                else{
                    var buttonSize by remember { mutableStateOf(IntSize.Zero) }
                    Button(
                        onClick = {
                            //toast
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD5D5D5)),
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(50.dp)
                            .border(
                                width = 1.dp,
                                color = tintGrey,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .onGloballyPositioned { coordinates ->
                                buttonSize = coordinates.size
                            }
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .drawBehind {
                                drawLine(
                                    color = tintGrey,
                                    start = Offset(
                                        -5 * size.width,
                                        size.height / 2
                                    ),
                                    end = Offset(
                                        5 * size.width,
                                        size.height / 2
                                    ),
                                    strokeWidth = 1.dp.toPx()
                                )
                            }
                        ) {
                            Text(
                                text = clothSize,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
