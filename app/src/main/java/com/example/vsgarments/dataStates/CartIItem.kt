package com.example.vsgarments.dataStates

data class CartIItem(
    val productItem: ProductItem ,
    val quantity : Int ,
    val selectedColor : Int? = null ,
    val selectedSize : String? = null
){
    constructor() : this(
        productItem = ProductItem() ,
        1 ,
        null ,
        null
    )
}
