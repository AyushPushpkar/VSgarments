package com.example.vsgarments.view_functions

import android.content.Context
import com.emreesen.sntoast.SnToast
import com.emreesen.sntoast.Type
import com.example.vsgarments.R

fun customToast(context: Context, message: String){
    SnToast.Builder()
        .context(context)
        .type(Type.WARNING)
        .message(message)
        //.cancelable(false or true) Optional Default: False
        // .iconSize(int size) Optional Default: 34dp
        // .textSize(int size) Optional Default 18sp
        .animation(true) //Optional Default: True
        .duration(3000)// Optional Default: 3000ms
        .backgroundColor(R.color.topbar_dark_blue)
        // .icon(R.drawable.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
        .build()
}