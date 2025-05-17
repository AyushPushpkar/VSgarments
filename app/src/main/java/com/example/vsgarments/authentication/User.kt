package com.example.vsgarments.authentication

import android.net.Uri
import com.example.vsgarments.dataStates.AddressInfo

data class User(
    val userName: String,
    val email: String,
    val mobileNumber: String = "",
    val addresses: List<AddressInfo> = emptyList(),
    val alterEmail: String = "",
    val alterMobileNumber: String = "",
    val state: String = "",
    val city: String = "",
    val pincode: String = "",
    val localImageUri: Uri? = null, // For local images
    val remoteImageUrl: String? = null, // For remote image URLs
    ){

    constructor() : this("" ,""   ,"", emptyList() , "" ,"","","","")
}
