package com.example.vsgarments.view_functions

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey

@Composable
fun number_editText(
    hint : String ,
    char_no : Int ,
    font_Family: FontFamily
){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text ,
        onValueChange = {
            text = it.filter {char ->
                char.isDigit()
            }.take(char_no)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
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
            fontFamily = font_Family,
            fontWeight = FontWeight.Normal,
            color = textcolorblue
        ) },
        modifier = Modifier
    )
}

@Composable
fun char_editText(
    hint : String ,
    font_Family: FontFamily
){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text ,
        onValueChange = {
            text = it
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
            fontFamily = font_Family,
            fontWeight = FontWeight.Normal,
            color = textcolorblue
        ) },
        modifier = Modifier

    )
}