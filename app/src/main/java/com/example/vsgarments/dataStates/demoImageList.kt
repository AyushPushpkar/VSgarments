package com.example.vsgarments.dataStates

import com.example.vsgarments.R
import kotlin.random.Random

fun generateRandomSizeToPriceMap(): Map<String, Pair<Int, Int>> {
    val sizes = listOf("XS", "S", "M", "L", "XL", "XXL")
    return sizes.associateWith {
        val currPrice = Random.nextInt(300, 700)
        val ogPrice = currPrice + Random.nextInt(50, 200)
        currPrice to ogPrice
    }
}

val imageList = listOf(
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 300,
        ogprice = 400,
        name = "Kipo and the Age of Wonderbeasts",
        CompanyName = "The VS Garments",
        rating = 4.0f,
        minQuantity = 1,
        maxQuantity = 50,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 350,
        ogprice = 450,
        name = "Aryan",
        CompanyName = "The VS Garments",
        rating = 4.2f,
        minQuantity = 4,
        maxQuantity = 16,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 400,
        ogprice = 500,
        name = "Eterna",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 2,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 320,
        ogprice = 420,
        name = "Nimbus",
        CompanyName = "The VS Garments",
        rating = 4.1f,
        minQuantity = 1,
        maxQuantity = 444,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 360,
        ogprice = 460,
        name = "Luna",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 50,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 410,
        ogprice = 510,
        name = "Solaris",
        CompanyName = "The VS Garments",
        rating = 4.6f,
        minQuantity = 4,
        maxQuantity = 100,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 340,
        ogprice = 440,
        name = "Zephyr",
        CompanyName = "The VS Garments",
        rating = 4.4f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 390,
        ogprice = 490,
        name = "Aurora",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 420,
        ogprice = 520,
        name = "Vortex",
        CompanyName = "The VS Garments",
        rating = 4.7f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 360,
        ogprice = 460,
        name = "Stellar",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") } ,
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 300,
        ogprice = 400,
        name = "Kipo and the Age of Wonderbeasts",
        CompanyName = "The VS Garments",
        rating = 4.0f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 350,
        ogprice = 450,
        name = "Aryan",
        CompanyName = "The VS Garments",
        rating = 4.2f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 400,
        ogprice = 500,
        name = "Eterna",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 320,
        ogprice = 420,
        name = "Nimbus",
        CompanyName = "The VS Garments",
        rating = 4.1f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 360,
        ogprice = 460,
        name = "Luna",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 410,
        ogprice = 510,
        name = "Solaris",
        CompanyName = "The VS Garments",
        rating = 4.6f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 340,
        ogprice = 440,
        name = "Zephyr",
        CompanyName = "The VS Garments",
        rating = 4.4f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 390,
        ogprice = 490,
        name = "Aurora",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 420,
        ogprice = 520,
        name = "Vortex",
        CompanyName = "The VS Garments",
        rating = 4.7f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 360,
        ogprice = 460,
        name = "Stellar",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap()
    ).apply { updatePriceBasedOnSize("S") }

)
