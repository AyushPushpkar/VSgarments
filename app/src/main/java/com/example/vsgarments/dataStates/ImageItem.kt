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
    val sizeToPriceMap: Map<String, Pair<Int, Int>>
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

}

