 package com.example.vsgarments.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vsgarments.R
import com.example.vsgarments.fontBaloo
import com.example.vsgarments.ui.theme.VSgarmentsTheme
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.splashdarkblue
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VSgarmentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EditProfileScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
//                    Splash_Screen(
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
        window.statusBarColor = textcolorgrey.hashCode()
    }
}

@Composable
fun EditProfileScreen(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {

        var namestate by remember {
            mutableStateOf("")
        }
        var numberstate by remember {
            mutableStateOf("")
        }
        var emailstate by remember {
            mutableStateOf("")
        }
        var statestate by remember {
            mutableStateOf("")
        }
        var citystate by remember {
            mutableStateOf("")
        }
        var pincodestate by remember {
            mutableStateOf("")
        }
        var alternatenumberstate by remember {
            mutableStateOf("")
        }
        var alternateemailstate by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ){

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                colors = CardDefaults.cardColors(appbackgroundcolor),
                shape = RoundedCornerShape(
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                ),
                elevation = CardDefaults.cardElevation(5.dp)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.987f)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(
                            topbarlightblue
                        )
                        .padding(bottom = 58.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box (
                        modifier = Modifier
                            .size(116.dp)
                            .clip(
                                shape = RoundedCornerShape(58.dp)
                            )
                            .background(Color.Gray)
                            .border(
                                width = 3.dp,
                                color = appbackgroundcolor,
                                shape = RoundedCornerShape(58.dp)
                            )
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.waddle_dees),
                            contentDescription = "Your image description",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.fillMaxWidth(0.57f))
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(appbackgroundcolor)
                                .border(
                                    width = 1.5.dp,
                                    color = topbardarkblue,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(5.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Image(painter = painterResource(id = R.drawable.edit_pen), contentDescription = "edit pen")
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.013f)
                        .background(appbackgroundcolor)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 23.dp,
                            bottomEnd = 23.dp
                        )
                    )
                    .background(appbackgroundcolor)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(426.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                        .padding(20.dp)
                ){
                    Text(
                        text = "Full Name *" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        value = namestate,
                        onValueChange = { namestate = it } ,
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
                            disabledContainerColor = Color.White ,
                            cursorColor = textcolorblue,
                        ),
                        )
                    
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Mobile Number *" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .heightIn(max = 52.dp),
                        value = numberstate,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        onValueChange = {
                            numberstate = it.filter {char ->
                                char.isDigit()
                            }.take(10)
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
                            cursorColor = textcolorblue,
                            ),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Email Id *" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        value = emailstate,
                        onValueChange = { emailstate = it } ,
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
                            disabledContainerColor = Color.White ,
                            cursorColor = textcolorblue,
                        ),
                    )

                    Spacer(modifier = Modifier.height(25.dp))



                    Box (
                        modifier = Modifier
                            .fillMaxWidth() ,
                        contentAlignment = Alignment.Center
                    ){
                        Button(
                            modifier = Modifier.fillMaxWidth(0.35f),
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
                                text = "Save" ,
                                color = Color.White ,
                                fontFamily = fontBaloo ,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(appbackgroundcolor)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 23.dp,
                            bottomEnd = 23.dp
                        )
                    )
                    .background(appbackgroundcolor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(426.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                        .padding(20.dp)
                ){
                    Text(
                        text = "State *" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        value = statestate,
                        onValueChange = { statestate = it } ,
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
                            disabledContainerColor = Color.White ,
                            cursorColor = textcolorblue,
                        ),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "City *" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        value = citystate,
                        singleLine = true,
                        onValueChange = {
                            citystate = it
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
                            cursorColor = textcolorblue,
                        ),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Pin Code *" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .heightIn(max = 52.dp),
                        value = pincodestate,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        onValueChange = {
                            pincodestate = it.filter {char ->
                                char.isDigit()
                            }.take(6)
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
                            cursorColor = textcolorblue,
                        ),
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable(
                                enabled = true,
                                indication = rememberRipple(
                                    color = topbarlightblue
                                ),
                                interactionSource = remember {
                                    MutableInteractionSource()
                                }
                            ) {
                            },
                        text = " Update ",
                        softWrap = true,
                        style = TextStyle(
                            fontSize = 25.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium ,
                            textAlign = TextAlign.Center,
                            color = topbardarkblue
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(appbackgroundcolor)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(310.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 23.dp,
                            bottomEnd = 23.dp
                        )
                    )
                    .background(appbackgroundcolor)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(306.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                        .padding(20.dp)
                ){
                    Text(
                        text = "Alternate Mobile Number" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .heightIn(max = 52.dp),
                        value = alternatenumberstate,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        onValueChange = {
                            alternatenumberstate = it.filter {char ->
                                char.isDigit()
                            }.take(10)
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
                            cursorColor = textcolorblue,
                        ),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Alternate Email Id" ,
                        color = textcolorblue ,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    TextField(
                        textStyle = TextStyle(
                            fontSize = 18.sp ,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        value = alternateemailstate,
                        onValueChange = { alternateemailstate = it } ,
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
                            disabledContainerColor = Color.White ,
                            cursorColor = textcolorblue,
                        ),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "This will help in recovery of your account " ,
                        color = Color(0xFF6188A0),
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(appbackgroundcolor)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color.White)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center

            ){
                Text(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                color = topbarlightblue
                            ),
                            interactionSource = remember {
                                MutableInteractionSource()
                            }
                        ) {
                        } ,
                    text = " Deactivate Account " ,
                    color = textcolorblue,
                    fontFamily = fontBaloo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )

                Box (
                    modifier
                        .fillMaxWidth(0.9f)
                        .height(2.dp)
                        .background(textcolorblue)
                )

                Text(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                color = topbarlightblue
                            ),
                            interactionSource = remember {
                                MutableInteractionSource()
                            }
                        ) {
                        },
                    text = " Delete Account " ,
                    color = textcolorblue,
                    fontFamily = fontBaloo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                
                Spacer(modifier = Modifier.height(20.dp))
            }

        }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp),
                        clip = false,
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    ),
                shape = RoundedCornerShape(
                    bottomStart = 5.dp,
                    bottomEnd = 5.dp
                ),
                elevation = CardDefaults.cardElevation(10.dp) ,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    topbardarkblue,
                                    topbarlightblue
                                )
                            )
                        )
                        .padding(
                            top = 24.dp,
                            start = 30.dp
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = "" , 
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = "Edit Profile",
                            fontSize = 23.sp ,
                            color = Color.Black,
                            fontFamily = fontBaloo ,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    
                }
            }


    }
}

