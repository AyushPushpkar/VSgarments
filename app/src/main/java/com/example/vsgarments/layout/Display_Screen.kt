package com.example.vsgarments.layout

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.vsgarments.R
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.cart.DetailsViewModel
import com.example.vsgarments.cart.CartIItem
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.dataStates.ProductItem
import com.example.vsgarments.product.ProductViewModel
import com.example.vsgarments.view_functions.ExpandableText3
import com.example.vsgarments.view_functions.SizeSelection
import com.example.vsgarments.view_functions.customToast
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun DisplayScreen(
    modifier: Modifier,
    navController: NavController,
    productItem: ProductItem?
) {

    val updatedImageItem = remember { mutableStateOf(productItem) }

    val context = LocalContext.current
    val productViewModel: ProductViewModel = hiltViewModel()
    val productState by productViewModel.productState.collectAsState()

    val detailsViewModel : DetailsViewModel = hiltViewModel()
    val cartState by detailsViewModel.addToCart.collectAsState()

    var isAddingToCart by remember { mutableStateOf(false) }


    when (cartState) {
        is Resource.Loading -> {
        }
        is Resource.Success -> {
            if (isAddingToCart) {
                customToast(context = context , "Added to Cart" , cancelable = true)
                isAddingToCart = false  // Reset flag after showing toast
            }

        }
        is Resource.Error -> {

            customToast(context = context , (cartState as Resource.Error).errorMassage ?: "Unknown error")
        }
        else -> Unit
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {
        val displayScrollview = rememberScrollState()
        Column(
            modifier = Modifier
                .background(appbackgroundcolor)
                .fillMaxSize()
                .verticalScroll(displayScrollview)
        ) {

            updatedImageItem.value?.let { item ->
                val selectedSize = remember { mutableStateOf(item.size ?: "S") }
                val currentPrice = remember { mutableIntStateOf(item.currprice) }
                val originalPrice = remember { mutableIntStateOf(item.ogprice) }

                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    Spacer(modifier = Modifier.height(70.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp),
                        colors = CardDefaults.cardColors(appbackgroundcolor),
                        shape = RoundedCornerShape(
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        ),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f)
                                .clip(
                                    RoundedCornerShape(
                                        bottomStart = 25.dp,
                                        bottomEnd = 25.dp
                                    )
                                )
                                .background(
                                    appbackgroundcolor
                                )
                                .padding(
                                    start = 4.dp,
                                    end = 4.dp
                                ),
                        ) {
                            productItem?.remoteImageUrl?.let { imageUrl ->
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(imageUrl)
                                        .crossfade(true)
                                        .error(R.drawable.retail)
                                        .placeholder(R.drawable.custom)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterHorizontally)
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .clip(
                                            shape = RoundedCornerShape(
                                                topStart = 0.dp,
                                                topEnd = 0.dp,
                                                bottomStart = 25.dp,
                                                bottomEnd = 25.dp
                                            )
                                        ),
                                    contentScale = ContentScale.Crop ,
                                    onError = { error ->
                                        Log.e("AsyncImage", "Error loading image: ${error.result.throwable}")
                                    }
                                )
                            }

                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.01f)
                                .background(appbackgroundcolor)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }

                Column(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        )
                        .background(Color.White)
                        .padding(
                            vertical = 10.dp,
                            horizontal = 20.dp
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = item.CompanyName,
                        color = textcolorgrey,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = item.name,
                        color = textcolorgrey,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    val percentLess by remember(
                        originalPrice.intValue,
                        currentPrice.intValue
                    ) {
                        derivedStateOf {
                            if (originalPrice.intValue > 0) {
                                ((originalPrice.intValue - currentPrice.intValue) * 100) / originalPrice.intValue
                            } else {
                                0
                            }
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 2.dp),
                                text = "MRP ",
                                color = tintGrey,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                modifier = Modifier.padding(vertical = 2.dp),
                                text = "₹${originalPrice.intValue}",
                                color = tintGrey,
                                textDecoration = TextDecoration.LineThrough
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "₹${currentPrice.intValue}",
                                color = topbardarkblue,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 21.sp
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 200.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(
                                        object : Shape {
                                            override fun createOutline(
                                                size: Size,
                                                layoutDirection: LayoutDirection,
                                                density: Density,
                                            ): Outline {
                                                val path = Path().apply {
                                                    moveTo(
                                                        0f,
                                                        0f
                                                    )
                                                    lineTo(
                                                        0f,
                                                        size.height
                                                    )
                                                    lineTo(
                                                        size.width * 0.9f,
                                                        size.height
                                                    )
                                                    lineTo(
                                                        size.width,
                                                        0f
                                                    )
                                                    close()
                                                }
                                                return Outline.Generic(path)
                                            }
                                        }
                                    )
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                topbardarkblue,
                                                topbarlightblue
                                            )
                                        ),
                                    )
                                    .padding(
                                        start = 10.dp,
                                        end = 15.dp,
                                        top = 2.dp,
                                        bottom = 2.dp
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${percentLess}% off!",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    if (
                        item.sizeToStockMap.all { !it.value }
                    ) {
                        Text(
                            text = "Out of Stock !",
                            color = topbarlightblue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = "Select Size",
                        color = textcolorgrey,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    SizeSelection(
                        imageItem = item,
                        onSizeUpdated = {
                            updatedImageItem.value = it
                            currentPrice.intValue = it.currprice
                            originalPrice.intValue = it.ogprice
                            selectedSize.value = it.size ?: ""
                            Log.d("SizeSelection", "Selected size: ${it.size}")
                        }
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    val product by remember(selectedSize.value) {
                        derivedStateOf {
                            updatedImageItem
                        }
                    }
                    Log.d("SizeSelection", "Selected product size: ${selectedSize.value}")

                    val cartProductItem by remember(selectedSize.value) {
                        derivedStateOf {
                            CartIItem(
                                productItem = updatedImageItem.value!!,
                                quantity = 1,
                                selectedSize = selectedSize.value
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(
                                vertical = 25.dp,
                                horizontal = 0.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                isAddingToCart = true
                                Log.d("CartProductItem", "Cart Product Details: $cartProductItem")
                                Log.d("CartProductItem", "Product Name: ${cartProductItem.productItem.name}")
                                Log.d("CartProductItem", "Product Size: ${cartProductItem.selectedSize}")
                                Log.d("CartProductItem", "Product Quantity: ${cartProductItem.quantity}")
                                Log.d("CartProductItem", "Product Current Price: ${cartProductItem.productItem.currprice}")
                                Log.d("CartProductItem", "Product Original Price: ${cartProductItem.productItem.ogprice}")
                                Log.d("CartProductItem", "Product Image URL: ${cartProductItem.productItem.remoteImageUrl}")
                                detailsViewModel.addUpdateProductInCart(cartProductItem)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0x66676767)
                            ),
                            contentPadding = PaddingValues()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                topbarlightblue,
                                                topbardarkblue
                                            )
                                        ),
                                        shape = RoundedCornerShape(0.dp)
                                    )
                                    .padding(
                                        horizontal = 17.dp,
                                        vertical = 11.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(17.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.edit_pen),
                                        contentDescription = "cart icon"
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Add to Cart",
                                    fontSize = 19.sp,
                                    color = Color.White,
                                    fontFamily = fontBaloo,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                navController.navigate(Screen.Wishlist.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0x66676767)
                            ),
                            contentPadding = PaddingValues()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                topbarlightblue,
                                                topbardarkblue
                                            )
                                        ),
                                        shape = RoundedCornerShape(0.dp)
                                    )
                                    .padding(
                                        horizontal = 17.dp,
                                        vertical = 11.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(17.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.edit_pen),
                                        contentDescription = "wishlist icon"
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Wishlist",
                                    fontSize = 19.sp,
                                    color = Color.White,
                                    fontFamily = fontBaloo,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = tintGrey,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(15.dp),
                    ) {
                        Text(
                            text = "Product Details" ,
                            color = textcolorgrey ,
                            fontWeight = FontWeight.SemiBold ,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        item.productDetails.keys.forEach { attr ->
                            Row {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = attr ,
                                    color = tintGrey
                                )
                                item.productDetails[attr]?.let {
                                    Text(
                                        text = it ,
                                        modifier = Modifier
                                            .weight(1f) ,
                                        color = textcolorgrey
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Details" ,
                            color = textcolorgrey ,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        
                        ExpandableText3(
                            item = item.description + item.description,
                            textColor = tintGrey
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            Column(
                modifier = Modifier
                    .background(appbackgroundcolor)
                    .padding(
                        horizontal = 5.dp,
                        vertical = 5.dp
                    )
            ) {
                Card(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(
                                5.dp
                            ),
                            clip = false,
                            ambientColor = Color.Black,
                            spotColor = Color.Black
                        ),
                    shape = RoundedCornerShape(
                        5.dp
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {

                    when (productState) {
                        is Resource.Loading -> {
                            // Show a loading indicator
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                        is Resource.Success -> {
                            val products =
                                (productState as Resource.Success<List<ProductItem>>).data

                            if (!products.isNullOrEmpty()) {

                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(
                                                RoundedCornerShape(5.dp)
                                            )
                                            .background(Color.White)
                                            .padding(horizontal = 8.dp)
                                    ) {
                                        items(products.size) {index->
                                            val product = products[index]
                                            DisplayItemCard(
                                                product = product,
                                                context = context,
                                                navController = navController
                                            )
                                        }
                                        item {
                                            Spacer(
                                                modifier = Modifier
                                                    .height(75.dp)
                                                    .background(appbackgroundcolor)
                                            )
                                        }
                                    }
                            } else {
                                Text(
                                    text = "No products available",
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }

                        is Resource.Error -> {
                            // Show error message
                            Text(
                                text = (productState as Resource.Error).errorMassage
                                    ?: "Unknown error",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Log.e(
                                "ProductScreen",
                                "Error loading products: ${productState.errorMassage}"
                            )
                        }

                        else -> {
                            // Handle unspecified state or no data
                            Text(
                                text = "No products available",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                )
                Card(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(
                                5.dp
                            ),
                            clip = false,
                            ambientColor = Color.Black,
                            spotColor = Color.Black
                        ),
                    shape = RoundedCornerShape(
                        5.dp
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    when (productState) {
                        is Resource.Loading -> {
                            // Show a loading indicator
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                        is Resource.Success -> {
                            val products =
                                (productState as Resource.Success<List<ProductItem>>).data

                            if (!products.isNullOrEmpty()) {

                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(
                                            RoundedCornerShape(5.dp)
                                        )
                                        .background(Color.White)
                                        .padding(horizontal = 8.dp)
                                ) {
                                    items(products.size) { index ->
                                        val product = products[index]
                                        DisplayItemCard(
                                            product = product,
                                            context = context,
                                            navController = navController
                                        )
                                    }
                                    item {
                                        Spacer(
                                            modifier = Modifier
                                                .height(75.dp)
                                                .background(appbackgroundcolor)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    text = "No products available",
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }

                        is Resource.Error -> {
                            // Show error message
                            Text(
                                text = (productState as Resource.Error).errorMassage
                                    ?: "Unknown error",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Log.e(
                                "ProductScreen",
                                "Error loading products: ${productState.errorMassage}"
                            )
                        }

                        else -> {
                            // Handle unspecified state or no data
                            Text(
                                text = "No products available",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    )
                    .background(Color.White)
            ) {

            }
        }
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                clip = false,
                ambientColor = Color.Black,
                spotColor = Color.Black
            ),
        shape = RoundedCornerShape(
            bottomStart = 5.dp,
            bottomEnd = 5.dp
        ),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            topbardarkblue,
                            topbarlightblue
                        )
                    )
                )
                .padding(
                    start = 30.dp
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            navController.navigate(Screen.Profile_Screen.route)
                        }
                )
                Spacer(modifier = Modifier.width(30.dp))
                if (productItem != null) {
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = productItem.CompanyName,
                            fontSize = 21.sp,
                            color = Color.Black,
                            fontFamily = fontBaloo,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,

                            )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(30.dp)
                        .padding(top = 13.dp)
                        .background(color = Color.Transparent)

                ) {
                    Image(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.Wishlist.route)

                            },
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "add account icon",
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(30.dp)
                        .padding(top = 11.dp)
                        .background(color = Color.Transparent)
                ) {
                    Image(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.CartScreen.route)

                            },
                        painter = painterResource(id = R.drawable.bitcoin_icons_cart_filled),
                        contentDescription = "add account icon"
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(30.dp)
                        .padding(top = 13.dp)
                        .background(color = Color.Transparent)
                ) {
                    Image(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.Profile_Screen.route)

                            },
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "add account icon"
                    )
                }
            }

        }
    }
}

@Composable
fun DisplayItemCard(
    product: ProductItem ,
    navController: NavController ,
    context: Context
) {

    val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp

    val imageItemJson = Gson().toJson(product)
    val encodedProductItem = URLEncoder.encode(imageItemJson, "UTF-8")


    Column(
        modifier = Modifier
            .background(Color.White)
            .width(160.dp)
            .fillMaxHeight()
            .padding(
                horizontal = 6.dp,
                vertical = 8.dp
            )
    ) {
        product.remoteImageUrl?.let { imageUrl ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .error(R.drawable.retail)
                    .placeholder(R.drawable.custom)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        navController.navigate("${Screen.DisplayScreen.route}/$encodedProductItem")
                    } ,
                contentScale = ContentScale.Crop ,
                onError = { error ->
                    Log.e("AsyncImage", "Error loading image: ${error.result.throwable}")
                }
            )
        } ?: Text(
            text = "No image available",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .padding(
                    horizontal = 5.dp,
                    vertical = 2.dp
                ),
            text = product.name,
            color = textcolorgrey,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 1.dp
                ),
                text = "₹${product.ogprice}",
                color = tintGrey,
                textDecoration = TextDecoration.LineThrough,
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 1.dp
                ),
                text = "₹${product.currprice}",
                color = textcolorblue,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        val percentless = percentLess(
            product.ogprice,
            product.currprice
        )
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 5.dp,
                        bottomEnd = 5.dp
                    )
                )
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFF9FFFB),
                            topbarlightblue
                        )
                    )
                )
                .padding(
                    start = 60.dp,
                    end = 20.dp
                )
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = 5.dp,
                        vertical = 1.dp
                    ),
                text = "${percentless}% off",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}