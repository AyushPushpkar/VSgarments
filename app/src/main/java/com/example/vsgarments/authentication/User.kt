package com.example.vsgarments.authentication

import com.example.vsgarments.dataStates.AddressInfo

data class User(
    val userName: String,
    val email: String,
    val imagePath: String = "" ,
    val mobileNumber: String = "" ,
    val addresses: List<AddressInfo> = emptyList(),
    val alterEmail: String = "" ,
    val alterMobileNumber: String = "" ,
    val state: String = "" ,
    val city: String = "" ,
    val pincode: String = "" ,

){
    constructor() : this("" ,"" ,"" , ""  , emptyList() , "" ,"","","","")
}
