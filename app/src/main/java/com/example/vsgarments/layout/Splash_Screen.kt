package com.example.vsgarments.layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.R
import com.example.vsgarments.ui.theme.fontKalnia
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.splashlightblue


@Composable
fun Splash_Screen(
    modifier: Modifier
){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        splashlightblue,
                        splashdarkblue
                    )
                )
            ),
    ){
        Box(modifier = Modifier
            .fillMaxHeight(0.80f)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = R.drawable.white_logo),
                contentDescription = "Aryan")
        }
        Box(modifier = Modifier
            .fillMaxHeight(.90f)
            .fillMaxWidth()
            .padding(30.dp),
            contentAlignment = Alignment.BottomCenter
        ){
              Text(
                  text = "VS Garments",
                  fontSize = 38.sp ,
                  color = Color.White ,
                  fontFamily = fontKalnia ,
                  fontWeight = FontWeight.Bold
              )
        }


    }

}