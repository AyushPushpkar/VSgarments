package com.example.vsgarments.dataStates

import com.example.vsgarments.R
import java.util.UUID

data class ImageItem(
    val imageresId: Int,
    val imageUrl: String = "" ,
    var currprice: Int,
    var ogprice: Int,
    var name: String,
    var CompanyName: String,
    var rating: Float,
    val minQuantity: Int,
    val maxQuantity: Int,
    val orderDate:String,
    val orderId: String,
    val orderType: String,
    val supplier: String,
    var size: String? = null,
    val sizeToPriceMap: Map<String, Pair<Int, Int>>,
    var inStock: Boolean = true,
    val sizeToStockMap: Map<String, Boolean>,
    val productDetails: Map<String , String>,
    var description: String,
) {
    fun updatePriceBasedOnSize(defaultSize: String? = null) {
        val selectedSize = size ?: defaultSize
        selectedSize?.let {
            sizeToPriceMap[it]?.let { (newCurrPrice, newOgPrice) ->
                currprice = newCurrPrice
                ogprice = newOgPrice
            }
        }
    }
    fun updateStockBasedOnSize(defaultSize: String? = null) {
        val selectedSize = size ?: defaultSize
        selectedSize?.let {
            sizeToStockMap[it]?.let { isInStock ->
                inStock = isInStock
            }
        }
    }

}

data class ProductItem(
    val id: String = UUID.randomUUID().toString(),
    val placeholderResId: Int = R.drawable.retail,
    val imageUrl: String = "",
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
    val sizeToPriceMap: Map<String, Pair<Int, Int>> = emptyMap(),
    var inStock: Boolean = true,
    val sizeToStockMap: Map<String, Boolean> = emptyMap(),
    val productDetails: Map<String, String> = emptyMap(),
    var description: String = "No description available"
) {
    fun updateDetailsBasedOnSize(defaultSize: String? = null) {
        val selectedSize = size ?: defaultSize
        selectedSize?.let {
            sizeToPriceMap[it]?.let { (newCurrPrice, newOgPrice) ->
                currprice = newCurrPrice
                ogprice = newOgPrice
            }
            sizeToStockMap[it]?.let { isInStock ->
                inStock = isInStock
            }
        }
    }

}


