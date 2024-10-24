package com.example.vsgarments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vsgarments.layout.HomeScreen
import com.example.vsgarments.layout.Profile_Screen
import com.example.vsgarments.layout.Signup_Screen
import com.example.vsgarments.ui.theme.VSgarmentsTheme
import com.example.vsgarments.ui.theme.textcolorgrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VSgarmentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    EditProfileScreen(modifier = Modifier.padding(innerPadding))
//                    Splash_Screen(modifier = Modifier.padding(innerPadding))
//                    Signup_Screen(modifier = Modifier.padding(innerPadding))
//                    HomeScreen(modifier = Modifier.padding(innerPadding))
//                    LoginScreen(modifier = Modifier.padding(innerPadding))
                    Profile_Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
        window.statusBarColor = textcolorgrey.hashCode()
    }
}


