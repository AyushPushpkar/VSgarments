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
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.char_editText
import com.example.vsgarments.view_functions.number_editText

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 50.dp , vertical = 30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {

        Spacer(
            modifier = Modifier
                .height(0.dp)
        )

        Image(
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.Profile_Screen.route)
                },
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
        var isEmail by remember { mutableStateOf(false) }

        Column {
            if (isEmail) {
                char_editText(
                    hint = "Email id",
                    font_Family = fontInter
                )
            } else {
                number_editText(
                    hint = "Mobile Number",
                    char_no = 10,
                    font_Family = fontInter
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            isEmail = !isEmail
                        },
                    text = if (isEmail) "Use Mobile number" else "Use Email-id",
                    color = textcolorblue,
                    fontSize = 12.sp,
                    fontFamily = fontInter,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        char_editText("Password" , fontInter)

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
                font_Family = fontInter ,
                onClick = {}
            )
        }
    }
}



