package com.example.vsgarments.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.char_editText

@Composable
fun Signup_Screen(
    modifier: Modifier,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 50.dp , vertical = 30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {

        Spacer(modifier = Modifier.height(0.dp))

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
        char_editText("Email or Mobile Number" , fontInter)
        char_editText("Password" , fontInter)
        char_editText("Confirm Password" , fontInter)
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
                button_text = "Sign Up",
                font_Family = fontBaloo ,
                onClick = {}
            )
        }
    }
}
