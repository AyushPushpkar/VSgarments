package com.example.vsgarments.dataStates

data class ImageItem(
    val imageresId: Int,
    var currprice: Int,
    var ogprice: Int,
    var name: String,
    var CompanyName: String,
    var rating: Float,
    val minQuantity: Int,
    val maxQuantity: Int,
    var size: String? = null,
    val sizeToPriceMap: Map<String, Pair<Int, Int>>,
    var inStock: Boolean = true,
    val sizeToStockMap: Map<String, Boolean>,
    val productDetails : Map<String , String>,
    var description : String,
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

