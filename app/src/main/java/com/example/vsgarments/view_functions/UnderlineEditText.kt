package com.example.vsgarments.view_functions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey

@Composable
fun number_textField(
    modifier: Modifier ,
    char_no : Int ,
    font_Family: FontFamily ,
    text: String ,
    onTextChange: (String) -> Unit ,
    enabled : Boolean = true ,
    focusRequester: FocusRequester
){

    TextField(
        enabled = enabled ,
        textStyle = TextStyle(
            fontSize = 18.sp ,
            fontFamily = font_Family ,
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .focusRequester(focusRequester)
            .heightIn(max = 52.dp),
        value = text,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        onValueChange = {
            onTextChange(
                it.filter {char ->
                    char.isDigit()
                }.take(char_no)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = appbackgroundcolor,
            focusedLabelColor =  textcolorblue,
            focusedTextColor = textcolorgrey,
            focusedIndicatorColor = textcolorblue,
            unfocusedLabelColor = textcolorblue,
            unfocusedIndicatorColor = textcolorblue,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = textcolorgrey,
            disabledContainerColor = Color.White ,
            disabledTextColor = textcolorgrey,
            disabledLabelColor = textcolorblue,
            disabledIndicatorColor = textcolorblue,
            cursorColor = textcolorblue,
        ),
    )
}

@Composable
fun text_textField(
    modifier: Modifier ,
    font_Family: FontFamily,
    text : String,
    onTextChange: (String) -> Unit ,
    enabled : Boolean = true ,
    focusRequester: FocusRequester
){

    TextField(
        enabled = enabled,
        textStyle = TextStyle(
            fontSize = 18.sp ,
            fontFamily = font_Family ,
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .focusRequester(focusRequester),
        value = text,
        onValueChange = { onTextChange(it) } ,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = appbackgroundcolor,
            focusedLabelColor =  textcolorblue,
            focusedTextColor = textcolorgrey,
            focusedIndicatorColor = textcolorblue,
            unfocusedLabelColor = textcolorblue,
            unfocusedIndicatorColor = textcolorblue,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = textcolorgrey,
            disabledTextColor = textcolorgrey,
            disabledLabelColor = textcolorblue,
            disabledIndicatorColor = textcolorblue,
            disabledContainerColor = Color.White ,
            cursorColor = textcolorblue,
        ),
    )
}