package com.example.vsgarments.dataStates

import com.example.vsgarments.R
import kotlin.random.Random

fun generateRandomSizeToPriceMap(): Map<String, Pair<Int, Int>> {
    val sizes = listOf("XS", "S", "M", "L", "XL", "XXL")
    return sizes.associateWith { // creates map
        val currPrice = Random.nextInt(300, 700)
        val ogPrice = currPrice + Random.nextInt(50, 200)
        currPrice to ogPrice // pair
    }
}

fun generateRandomSizeToStockMap(): Map<String, Boolean> {
    val sizes = listOf("XS", "S", "M", "L", "XL", "XXL")
    return sizes.associateWith {
        Random.nextBoolean()
    }
}

fun generateRandomAttributes(): Map<String, String> {
    val attributes = listOf("Color", "Fabric", "Sleeve", "Pack of", "Pattern", "Occasion" , "Fit")
    val randomValues = mapOf(
        "Color" to listOf("Red", "Blue", "Green", "Black", "White"),
        "Fabric" to listOf("Cotton", "Polyester", "Linen", "Wool", "Silk"),
        "Sleeve" to listOf("Full Sleeve", "Half Sleeve", "Sleeveless"),
        "Pack of" to listOf("1", "2", "3", "4"),
        "Pattern" to listOf("Solid", "Striped", "Checked", "Floral", "Polka Dot"),
        "Occasion" to listOf("Casual", "Formal", "Party", "Sports"),
        "Fit" to listOf("Slim Fit", "Regular Fit", "Loose Fit")
    )

    return attributes.associateWith { attr ->
        randomValues[attr]?.random() ?: "Unknown"
    }
}

fun generateClothDescriptionParagraph(productDetails: Map<String, String>): String {
    val color = productDetails["Color"] ?: "unknown color"
    val fabric = productDetails["Fabric"] ?: "unknown fabric"
    val sleeve = productDetails["Sleeve"] ?: "unspecified sleeve style"
    val packOf = productDetails["Pack of"] ?: "unknown pack"
    val pattern = productDetails["Pattern"] ?: "no specific pattern"
    val occasion = productDetails["Occasion"] ?: "unspecified occasion"
    val fit = productDetails["Fit"] ?: "universal fit"

    return "This outfit is designed with a beautiful $color color and crafted from premium $fabric material." +
            " It features a $sleeve design, perfect for $occasion wear. With its $pattern pattern and $fit," +
            " it ensures style and comfort." +
            " This clothing item is a great addition to any wardrobe."
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
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap() ,
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
            },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 350,
        ogprice = 450,
        name = "Aryan",
        CompanyName = "The VS Garments",
        rating = 4.2f,
        minQuantity = 4,
        maxQuantity = 16,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = mapOf(
            "XS" to false
            ,"M" to false
            ,"S" to false
            ,"L" to false
            ,"XL" to false
            ,"XXL" to false
        ),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 400,
        ogprice = 500,
        name = "Eterna",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 2,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 320,
        ogprice = 420,
        name = "Nimbus",
        CompanyName = "The VS Garments",
        rating = 4.1f,
        minQuantity = 1,
        maxQuantity = 444,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 360,
        ogprice = 460,
        name = "Luna",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 50,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 410,
        ogprice = 510,
        name = "Solaris",
        CompanyName = "The VS Garments",
        rating = 4.6f,
        minQuantity = 4,
        maxQuantity = 100,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 340,
        ogprice = 440,
        name = "Zephyr",
        CompanyName = "The VS Garments",
        rating = 4.4f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 390,
        ogprice = 490,
        name = "Aurora",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 420,
        ogprice = 520,
        name = "Vortex",
        CompanyName = "The VS Garments",
        rating = 4.7f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 360,
        ogprice = 460,
        name = "Stellar",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 300,
        ogprice = 400,
        name = "Kipo and the Age of Wonderbeasts",
        CompanyName = "The VS Garments",
        rating = 4.0f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 350,
        ogprice = 450,
        name = "Aryan",
        CompanyName = "The VS Garments",
        rating = 4.2f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 400,
        ogprice = 500,
        name = "Eterna",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 320,
        ogprice = 420,
        name = "Nimbus",
        CompanyName = "The VS Garments",
        rating = 4.1f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 360,
        ogprice = 460,
        name = "Luna",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 410,
        ogprice = 510,
        name = "Solaris",
        CompanyName = "The VS Garments",
        rating = 4.6f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 340,
        ogprice = 440,
        name = "Zephyr",
        CompanyName = "The VS Garments",
        rating = 4.4f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 390,
        ogprice = 490,
        name = "Aurora",
        CompanyName = "The VS Garments",
        rating = 4.5f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.custom,
        currprice = 420,
        ogprice = 520,
        name = "Vortex",
        CompanyName = "The VS Garments",
        rating = 4.7f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 360,
        ogprice = 460,
        name = "Stellar",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 1,
        maxQuantity = 10,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply {
        description = generateClothDescriptionParagraph(productDetails = this.productDetails)
        updatePriceBasedOnSize("S")
    },

)
