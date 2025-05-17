package com.example.vsgarments.cart

import com.example.vsgarments.dataStates.ProductItem

data class CartIItem(
    val productItem: ProductItem,
    val quantity : Int,
    val selectedColor : String? = null,
    val selectedSize : String? = null
){
    constructor() : this(
        productItem = ProductItem() ,
        1 ,
        null ,
        null
    )
}
