package com.example.vsgarments.wishlist

import com.example.vsgarments.dataStates.ProductItem

data class WishlistItem(
    val productItem: ProductItem,
    val selectedColor : Int? = null,
){
    constructor() : this(
        productItem = ProductItem() ,
        null ,
    )
}
