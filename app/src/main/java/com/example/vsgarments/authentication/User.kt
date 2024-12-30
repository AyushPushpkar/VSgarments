package com.example.vsgarments.authentication

data class User(
    val userName: String,
    val email: String,
    val imagePath: String = "" ,
    val mobileNumber: String = "" ,
    val state: String = "" ,
    val city: String = "" ,
    val pincode: String = "" ,
    val alterEmail: String = "" ,
    val alterMobileNumber: String = "" ,

){
    constructor() : this("" ,"" ,"" , "" , "" , "" , "" , "" , "" )
}
