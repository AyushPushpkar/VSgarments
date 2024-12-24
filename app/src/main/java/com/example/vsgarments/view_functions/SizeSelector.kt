package com.example.vsgarments.view_functions

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vsgarments.dataStates.ImageItem
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.tintGrey

@Composable
fun SizeSelection(
    imageItem: ImageItem,
    onSizeUpdated: (ImageItem) -> Unit
) {
    val availableSizes = imageItem.sizeToPriceMap.keys.toList()
    var selectedSize by remember { mutableStateOf(imageItem.size ?: "S") }

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
            availableSizes.forEach { size ->
                val isSelected = selectedSize == size
                val backgroundColor = if (isSelected) appbackgroundcolor else Color.White

                Button(
                    onClick = {
                        selectedSize = size
                        imageItem.size = size
                        imageItem.updatePriceBasedOnSize()
                        onSizeUpdated(imageItem)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(50.dp)
                        .border(width = 1.dp, color = tintGrey, shape = RoundedCornerShape(10.dp))
                ) {
                    Text(text = size)
                }
            }
        }
    }
}
