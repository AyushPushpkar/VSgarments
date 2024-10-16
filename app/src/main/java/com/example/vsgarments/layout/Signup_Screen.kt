package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.R
import com.example.vsgarments.fontInter
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.topbardarkblue

@Composable
fun Signup_Screen() {

    Column(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "",
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier
                .height(0.dp)
        )

        Text(
            text = "Create your account",
            color = textcolorgrey,
            fontSize = 20.sp,
            fontFamily = fontInter,
            fontWeight = FontWeight.SemiBold
        )
        editText("Email or Mobile Number")
        editText("Password")
        editText("Confirm Password")
        Spacer(
            modifier = Modifier
                .height(0.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {

            Button(
                onClick = {} ,
                colors = ButtonDefaults.buttonColors(containerColor = textcolorblue),
                modifier = Modifier
                    .fillMaxWidth(.5f)
            ){

                Text(
                    text = "Sign Up",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontFamily = fontInter,
                    fontWeight = FontWeight.Normal,
                )


            }
        }
    }
}
