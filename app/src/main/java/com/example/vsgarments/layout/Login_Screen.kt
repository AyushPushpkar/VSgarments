package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.R
import com.example.vsgarments.fontBaloo
import com.example.vsgarments.fontInter
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.editText

@Composable
fun LoginScreen(
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {

        Spacer(
            modifier = Modifier
                .height(0.dp)
        )

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
            text = "Login to your account",
            color = textcolorgrey,
            fontSize = 20.sp,
            fontFamily = fontInter,
            fontWeight = FontWeight.SemiBold
        )
        editText("Email or Mobile Number")
        editText("Password")

        Row {
            Text(
                text = "Forget your password ? ",
                color = textcolorgrey,
                fontSize = 13.sp,
                fontFamily = fontInter,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = " Reset here",
                color = topbardarkblue,
                fontSize = 13.sp,
                fontFamily = fontInter,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .clickable {
                    }
            )
        }
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
            blue_Button(
                modifier = Modifier,
                width_fraction = 0.5f,
                button_text = "Login",
                font_Family = fontInter
            )
        }
    }
}



