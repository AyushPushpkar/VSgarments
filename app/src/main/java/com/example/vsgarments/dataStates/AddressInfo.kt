package com.example.vsgarments.dataStates

data class AddressInfo(
    val address: String = "",
    val name: String = "",
    val pincode: String = "" ,
    val city: String = "" ,
    val state: String = "" ,
    val kindOfPlace: PlaceType = PlaceType.OTHER,

){
    constructor() : this(
        address = "",
        name = "",
        pincode = "",
        city = "",
        state = "",
        kindOfPlace = PlaceType.OTHER,
    )
}

enum class PlaceType {
    HOME, WORK, OTHER
}