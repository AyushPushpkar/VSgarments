package com.example.vsgarments.view_functions
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun blue_Button(
    modifier: Modifier ,
    width_fraction: Float ,
    button_text : String ,
    font_Family: FontFamily
) {
    Button(
        modifier = Modifier.fillMaxWidth(width_fraction),
        contentPadding = ButtonDefaults.ContentPadding,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 7.dp
        ),
        colors= ButtonDefaults.buttonColors(
            containerColor = Color(0xFF03A9F4)
        ),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = button_text,
            color = Color.White,
            fontFamily = font_Family,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}