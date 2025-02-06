package com.example.vsgarments.view_functions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

@Composable
fun blue_Button(
    modifier: Modifier = Modifier,
    width_fraction: Float ,
    button_text : String ,
    font_Family: FontFamily ,
    enabled : Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled ,
        modifier = Modifier
            .fillMaxWidth(width_fraction)
            .padding(
                start = 10.dp ,
                end = 10.dp
            ),
        contentPadding = PaddingValues(),
        colors= ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 7.dp
        ),
        onClick = onClick
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            topbarlightblue ,
                            topbardarkblue
                        )
                    ),
                    shape = RoundedCornerShape(0.dp)
                )
                .clip(
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(
                    horizontal = 16.dp ,
                    vertical = 10.dp
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = button_text,
                color = Color.White,
                fontFamily = font_Family,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun loading_Button(
    modifier: Modifier = Modifier,
    width_fraction: Float ,
    button_text : String ,
    font_Family: FontFamily ,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(width_fraction)
            .padding(
                start = 10.dp ,
                end = 10.dp
            ),
        contentPadding = PaddingValues(),
        colors= ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 7.dp
        ),
        onClick = onClick
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            topbarlightblue ,
                            topbardarkblue
                        )
                    ),
                    shape = RoundedCornerShape(0.dp)
                )
                .clip(
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(
                    horizontal = 16.dp ,
                    vertical = 10.dp
                ),
            contentAlignment = Alignment.Center
        ){
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = button_text,
                    color = Color.White,
                    fontFamily = font_Family,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }
    }
}