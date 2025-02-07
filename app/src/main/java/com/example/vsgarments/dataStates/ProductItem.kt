package com.example.vsgarments.dataStates

import android.net.Uri
import com.example.vsgarments.R
import java.util.UUID

enum class Size {
    XS, S, M, L, XL, XXL, Free
}

data class SizePrice(val currentPrice: Int, val originalPrice: Int){

    constructor() : this (
        currentPrice = 0,
        originalPrice = 0
    )
}

data class ProductItem(
    val id: String = UUID.randomUUID().toString(),
    val placeholderResId: Int = R.drawable.retail,
    val localImageUri: Uri? = null, // For local images
    val remoteImageUrl: String? = null , // For remote image URLs
    var currprice: Int = 0,
    var ogprice: Int = 0,
    var name: String,
    var CompanyName: String = "Unknown",
    var rating: Float = 0.0f,
    val minQuantity: Int = 1,
    val maxQuantity: Int = 500,
    val orderDate: String = "",
    val orderId: String = "",
    val orderType: String = "Unknown",
    val supplier: String = "Unknown",
    var size: String? = null,
    val sizeToPriceMap: Map<String, SizePrice> = emptyMap(),
    var inStock: Boolean = true,
    val sizeToStockMap: Map<String, Boolean> = emptyMap(),
    val productDetails: Map<String, String> = emptyMap(),
    var description: String = "No description available"
) {

    constructor() : this(
        id = UUID.randomUUID().toString(),
        placeholderResId = R.drawable.retail,
        currprice = 0,
        ogprice = 0,
        name = "",
        CompanyName = "Unknown",
        rating = 0.0f,
        minQuantity = 1,
        maxQuantity = 500,
        orderDate = "",
        orderId = "",
        orderType = "Unknown",
        supplier = "Unknown",
        size = null,
        sizeToPriceMap = mapOf(
            "XS" to SizePrice(0, 0),
            "S" to SizePrice(0, 0),
            "M" to SizePrice(0, 0),
            "L" to SizePrice(0, 0),
            "XL" to SizePrice(0, 0),
            "XXL" to SizePrice(0, 0),
        ),
        inStock = true,
        sizeToStockMap = emptyMap(),
        productDetails = emptyMap(),
        description = "No description available"
    )

    fun updatePriceBasedOnSize(defaultSize: String? = null) {
        val selectedSize = size ?: defaultSize
        selectedSize?.let {
            sizeToPriceMap[it]?.let { (newCurrPrice, newOgPrice) ->
                currprice = newCurrPrice
                ogprice = newOgPrice
            }
        }
    }

}


