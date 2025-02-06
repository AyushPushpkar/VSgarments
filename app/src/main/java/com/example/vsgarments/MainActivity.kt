package com.example.vsgarments

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.vsgarments.layout.Wishlist
import com.example.vsgarments.navigation.App_Navigation
import com.example.vsgarments.navigation.BottomNavBar
import com.example.vsgarments.navigation.Bottom_Nav_item
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.VSgarmentsTheme
import com.example.vsgarments.ui.theme.textcolorgrey
//import com.razorpay.Checkout
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            VSgarmentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize() ,
                ) { innerPadding ->
                    App_Navigation(modifier = Modifier.padding(innerPadding) )
                }
            }
        }
        window.statusBarColor = textcolorgrey.hashCode()
    }


//    fun startPayment(context: Context, amount: Float) {
//        val activity = context as MainActivity
//        val checkout = Checkout()
//        checkout.setKeyID("Enter your key here")
//
//        val amt = Math.round(amount * 100)
//
//        val obj = JSONObject()
//        try {
//            obj.put("name", "Geeks for Geeks")
//            obj.put("description", "Test payment")
//            obj.put("theme.color", "#3399cc")
//            obj.put("currency", "INR")
//            obj.put("amount", amt)
//            obj.put("prefill.contact", "9284064503")
//            obj.put("prefill.email", "chaitanyamunje@gmail.com")
//
//            checkout.open(activity, obj)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//    }
}


