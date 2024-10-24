package com.example.vsgarments.view_functions

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.vsgarments.fontInter
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey

@Composable
fun editText(
    hint : String
){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text ,
        onValueChange = {
                newText->text = newText
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = textcolorblue,
            focusedLabelColor =  textcolorblue,
            focusedTextColor = textcolorgrey,
            unfocusedBorderColor = textcolorblue,
            unfocusedLabelColor = textcolorblue,
            unfocusedTextColor = textcolorgrey,
            cursorColor = textcolorblue,
        ),
        singleLine = true,
        label = { Text(
            text = hint,
            fontFamily = fontInter,
            fontWeight = FontWeight.Normal,
            color = textcolorblue
        ) },
        modifier = Modifier

    )
}