package com.example.vsgarments.view_functions

import android.view.Menu
import android.widget.Spinner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey

@Composable
fun Spinner(
    modifier: Modifier ,
    itemList: List<String> ,
    selectedItem : String ,
    onItemSelected : (selectedItem : String) -> Unit ,
    initiallyOpened : Boolean = false ,
    spinnerwidth : Dp
){
    var tempselectedItem = selectedItem

    if(tempselectedItem.isBlank() && itemList.isNotEmpty()){
        onItemSelected(itemList[0])
        tempselectedItem = itemList[0]
    }
    var expanded by rememberSaveable() {
        mutableStateOf(initiallyOpened)
    }

    Box(
        modifier = Modifier
            .width(spinnerwidth)
    ) {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth() ,
            contentPadding = PaddingValues(horizontal = 15.dp) ,
            enabled = itemList.isNotEmpty()
        ) {
            Text(
                text = "Qty : $tempselectedItem",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f),
                fontSize = 13.sp,
                color = textcolorgrey
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "DropDown",
                tint = tintGrey,
                modifier = Modifier
                    .clickable {
                        expanded = !expanded
                    }
                    .scale(
                        1f,
                        if (expanded) -1f else 1f
                    ) // mirror the icon
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .align(Alignment.TopStart)
                .width(spinnerwidth)
        ) {
            itemList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick = {
                        expanded = false
                        onItemSelected(it)
                    },
                    colors = MenuDefaults.itemColors(textcolorgrey),
                )
            }
        }
    }
}