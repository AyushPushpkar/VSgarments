package com.example.vsgarments.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vsgarments.ui.theme.VSgarmentsTheme

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
                }
            }
        }
        window.statusBarColor = Color.Cyan.hashCode()
    }
}

@Composable
fun EditProfileScreen(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCAF4FF))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ){

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .height(350.dp),
                colors = CardDefaults.cardColors(Color(0xFFCAF4FF)),
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
                            Color(0xFF4FD8FD)
                        ),
                ) {

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.013f)
                        .background(Color(0xFFCAF4FF))
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 23.dp,
                            bottomEnd = 23.dp
                        )
                    )
                    .background(Color(0xFFCAF4FF))

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.985f)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.015f)
                        .background(Color(0xFFCAF4FF))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 23.dp,
                            bottomEnd = 23.dp
                        )
                    )
                    .background(Color(0xFFCAF4FF))

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.985f)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.015f)
                        .background(Color(0xFFCAF4FF))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 23.dp,
                            bottomEnd = 23.dp
                        )
                    )
                    .background(Color(0xFFCAF4FF))

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.985f)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(Color.White)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.015f)
                        .background(Color(0xFFCAF4FF))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White)

            ){}

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f) ,
        ){
            Card(
                modifier = Modifier
                    .fillMaxSize() ,
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
                                    Color(0xFF2496FF),
                                    Color(0xFF44D7FF)
                                )
                            )
                        ),
                ) {
                    Text(
                        text = "Edit Profile"
                    )
                }
            }
        }

    }
}

