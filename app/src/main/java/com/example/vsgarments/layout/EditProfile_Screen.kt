package com.example.vsgarments.layout

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.authentication.RegisterViewModel
import com.example.vsgarments.authentication.User
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.dataStates.AddressInfo
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.customToast
import com.example.vsgarments.view_functions.number_textField
import com.example.vsgarments.view_functions.text_textField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun EditProfile_Screen(navController: NavController , modifier: Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {

        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        val context = LocalContext.current
        var userName by rememberSaveable {
            mutableStateOf("")
        }
        val email by rememberSaveable { mutableStateOf(currentUser?.email ?: "") }
        var mobileNumber by rememberSaveable { mutableStateOf("") }
        var alterEmail by rememberSaveable { mutableStateOf("") }
        var alterMobileNumber by rememberSaveable { mutableStateOf("") }
        var state by rememberSaveable { mutableStateOf("") }
        var city by rememberSaveable { mutableStateOf("") }
        var pincode by rememberSaveable { mutableStateOf("") }

        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        LaunchedEffect(Unit) {
            userName = sharedPreferences.getString("username", "") ?: ""
            mobileNumber = sharedPreferences.getString("mobileNumber", "") ?: ""
            alterEmail = sharedPreferences.getString("alterEmail", "") ?: ""
            alterMobileNumber = sharedPreferences.getString("alterMobileNumber", "") ?: ""
            state = sharedPreferences.getString("state", "") ?: ""
            city = sharedPreferences.getString("city", "") ?: ""
            pincode = sharedPreferences.getString("pincode", "") ?: ""
        }

        val registerViewModel : RegisterViewModel = hiltViewModel()
        val currentUserResource by registerViewModel.currentUser.collectAsState()

        var isEditing by rememberSaveable { mutableStateOf(false) }

        when (currentUserResource) {
            is Resource.Success -> {
                val user = (currentUserResource as Resource.Success<User>).data

                if (user != null && !isEditing) {
                    var hasChanges = false

                    if (userName != user.userName) {
                        userName = user.userName
                        hasChanges = true
                    }
                    if (mobileNumber != user.mobileNumber && user.mobileNumber.isNotEmpty()) {
                        mobileNumber = user.mobileNumber
                        hasChanges = true
                    }
                    if (alterEmail != user.alterEmail && user.alterEmail.isNotEmpty()) {
                        alterEmail = user.alterEmail
                        hasChanges = true
                    }
                    if (alterMobileNumber != user.alterMobileNumber && user.alterMobileNumber.isNotEmpty()) {
                        alterMobileNumber = user.alterMobileNumber
                        hasChanges = true
                    }
                    if (state != user.state && user.state.isNotEmpty()) {
                        state = user.state
                        hasChanges = true
                    }
                    if (city != user.city && user.city.isNotEmpty()) {
                        city = user.city
                        hasChanges = true
                    }
                    if (pincode != user.pincode && user.pincode.isNotEmpty()) {
                        pincode = user.pincode
                        hasChanges = true
                    }

                    // Only update SharedPreferences if there are changes
                    if (hasChanges) {
                        with(sharedPreferences.edit()) {
                            putString("username", user.userName)
                            putString("email", user.email)
                            putString("mobileNumber", user.mobileNumber)
                            putString("state", user.state)
                            putString("city", user.city)
                            putString("pincode", user.pincode)
                            putString("alterEmail", user.alterEmail)
                            putString("alterMobileNumber", user.alterMobileNumber)
                            apply()
                        }
                    }
                }
            }

            is Resource.Error -> {
                userName = ""
                mobileNumber = ""
                alterEmail = ""
                alterMobileNumber = ""
                state = ""
                city = ""
                pincode = ""
            }
            else -> {}
        }

        val editprofilescroll = rememberScrollState()

        val focusManager = LocalFocusManager.current
        val focusRequester = FocusRequester()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .verticalScroll(editprofilescroll)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                colors = CardDefaults.cardColors(appbackgroundcolor),
                shape = RoundedCornerShape(
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                ),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
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
                        .padding(bottom = 48.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
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
                    ) {
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
                            Image(
                                painter = painterResource(id = R.drawable.edit_pen),
                                contentDescription = "edit pen"
                            )
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
                ) {
                    Text(
                        text = "Full Name *",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    text_textField(
                        modifier = Modifier,
                        font_Family = fontInter ,
                        text = userName ,
                        onTextChange = {
                            userName = it
                            isEditing = true
                        } ,
                        focusRequester = focusRequester
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Mobile Number *",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    number_textField(
                        modifier = Modifier,
                        char_no = 10,
                        font_Family = fontInter ,
                        focusRequester = focusRequester,
                        text = mobileNumber ,
                        onTextChange = {
                            mobileNumber = it
                            isEditing = true
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Email Id *",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )

                    text_textField(
                        modifier = Modifier,
                        font_Family = fontInter,
                        text = email,
                        onTextChange = {} ,
                        enabled = false ,
                        focusRequester = focusRequester
                    )

                    Spacer(modifier = Modifier.height(25.dp))



                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        blue_Button(
                            modifier = Modifier,
                            width_fraction = 0.4f,
                            button_text = "Save",
                            font_Family = fontBaloo ,
                            onClick = {
                                if (mobileNumber.length == 10 && mobileNumber.all { it.isDigit() }) {
                                    val updatedFields = mapOf(
                                        "userName" to userName,
                                        "mobileNumber" to mobileNumber,
                                    )
                                    registerViewModel.updateUserDetails(updatedFields)

                                    isEditing = false
                                } else {
                                    customToast(context, "Please enter a valid 10-digit mobile number")
                                }
                            }
                        )
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
                ) {
                    Text(
                        text = "State *",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    text_textField(
                        modifier = Modifier,
                        font_Family = fontInter ,
                        text = state,
                        onTextChange = {
                            state = it
                            isEditing = true
                        } ,
                        focusRequester = focusRequester
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "City *",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    text_textField(
                        modifier = Modifier,
                        font_Family = fontInter,
                        text = city,
                        onTextChange = {
                            city = it
                            isEditing = true
                        } ,
                        focusRequester = focusRequester
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Pin Code *",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    number_textField(
                        modifier = Modifier,
                        char_no = 6,
                        font_Family = fontInter ,
                        focusRequester = focusRequester,
                        text = pincode,
                        onTextChange = {
                            pincode = it
                            isEditing = true
                        }
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
                                if (pincode.length == 6 && pincode.all { it.isDigit() }) {
                                    val updatedFields = mapOf(
                                        "state" to state,
                                        "city" to city,
                                        "pincode" to pincode
                                    )
                                    registerViewModel.updateUserDetails(updatedFields)
                                    isEditing = false
                                } else {
                                    customToast(context, "Please enter a valid 6-digit pincode")
                                }
                            },
                        text = " Update ",
                        softWrap = true,
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = fontBaloo,
                            fontWeight = FontWeight.Medium,
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
                    .height(350.dp)
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
                        .height(346.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Alternate Mobile Number",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    number_textField(
                        modifier = Modifier,
                        char_no = 10,
                        font_Family = fontInter ,
                        focusRequester = focusRequester,
                        text = alterMobileNumber ,
                        onTextChange = {
                            alterMobileNumber = it
                            isEditing = true
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Alternate Email Id",
                        color = textcolorblue,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    text_textField(
                        modifier = Modifier,
                        font_Family = fontInter ,
                        text = alterEmail,
                        onTextChange = {
                            isEditing = true
                            alterEmail = it
                        } ,
                        focusRequester = focusRequester
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "This will help in recovery of your account ",
                        color = Color(0xFF6188A0),
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))

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
                                if (alterMobileNumber.length == 10 && alterMobileNumber.all { it.isDigit() }) {

                                    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
                                    if (alterEmail.matches(Regex(emailPattern))) {
                                        val updatedFields = mapOf(
                                            "alterEmail" to alterEmail,
                                            "alterMobileNumber" to alterMobileNumber
                                        )
                                        registerViewModel.updateUserDetails(updatedFields)

                                        isEditing = false
                                    } else {
                                        customToast(context, "Please enter a valid email address")
                                    }
                                } else {
                                    customToast(context, "Please enter a valid 10-digit mobile number")
                                }
                            },
                        text = " Update ",
                        softWrap = true,
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = fontBaloo,
                            fontWeight = FontWeight.Medium,
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
                    .height(200.dp)
                    .background(Color.White)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center

            ) {
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
                    text = " Deactivate Account ",
                    color = textcolorblue,
                    fontFamily = fontBaloo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(2.dp)
                        .background(textcolorblue)
                )
                Spacer(modifier = Modifier.height(20.dp))

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
                            },
                        ) {
                        },
                    text = " Delete Account ",
                    color = textcolorblue,
                    fontFamily = fontBaloo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
            }

        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    clip = false,
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                ),
            shape = RoundedCornerShape(
                bottomStart = 5.dp,
                bottomEnd = 5.dp
            ),
            elevation = CardDefaults.cardElevation(10.dp),
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
                        start = 30.dp
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.navigate(Screen.Profile_Screen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Edit Profile",
                        fontSize = 23.sp,
                        color = Color.Black,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
        }


    }
}

