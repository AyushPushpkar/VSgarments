package com.example.vsgarments.layout

import android.app.Activity
import android.content.Context
import android.util.Log

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
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
import com.example.vsgarments.cart.CartIItem
import com.example.vsgarments.cart.CartViewModel
import com.example.vsgarments.dataStates.AddressInfo
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.rateboxGreen
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

import com.example.vsgarments.payment_methods.PaymentState
import com.example.vsgarments.payment_methods.PaymentViewModel
import com.example.vsgarments.view_functions.BlueButton
import com.example.vsgarments.view_functions.RadioButtons
import com.example.vsgarments.view_functions.Spinner
import com.example.vsgarments.view_functions.ToggleableInfo
import com.example.vsgarments.view_functions.customToast
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TmpScreen(
    modifier: Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    val paymentViewModel :  PaymentViewModel = hiltViewModel()
    val paymentState by paymentViewModel.paymentStatus.collectAsState()


    val numberFormat = NumberFormat.getInstance(Locale("en", "IN"))

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ) {

        var initiallyOpened by remember {
            mutableStateOf(false)
        }

        val selectedQuantities = remember { mutableMapOf<Int, Int>() }
        var totalCurrentPrice by remember { mutableDoubleStateOf(0.0) }
        var totalOgPrice by remember { mutableDoubleStateOf(0.0) }

        val cartViewModel: CartViewModel = hiltViewModel()
        val cartState by cartViewModel.cartProducts.collectAsState()

        val products = (cartState as? Resource.Success<List<CartIItem>>)?.data

        LaunchedEffect(products) {
            products?.forEachIndexed { index, item ->
                selectedQuantities[index] = item.productItem.minQuantity
            }
            if (!products.isNullOrEmpty()) {
                val (newTotalCurrent, newTotalOg) = recalculateTotal(products, selectedQuantities)
                totalCurrentPrice = newTotalCurrent
                totalOgPrice = newTotalOg
            }
        }

        Column(
            modifier = Modifier
                .background(appbackgroundcolor)
        ) {

            when (cartState) {
                is Resource.Loading -> {
                    LazyColumn {
                        val shimmerPlaceholders = List(8) { it }
                        val chunkedPlaceholders = shimmerPlaceholders.chunked(2)

                        items(chunkedPlaceholders.size) { chunkIndex ->
                            val chunk = chunkedPlaceholders[chunkIndex]

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                chunk.forEach {
                                    ShimmerLoadingEffect()
                                }
                            }
                        }
                    }

                }
                is Resource.Success -> {

                    if (!products.isNullOrEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            item {

                                val savedOption = getSavedAddressOption(context)
                                val address = savedOption.address
                                val pincode = savedOption.pincode
                                val name = savedOption.name

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                        .background(Color.White)
                                        .padding(
                                            start = 20.dp,
                                            end = 30.dp
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(75.dp)
                                            .background(appbackgroundcolor)
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(75.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth(0.7f)
                                        ) {

                                            Text(
                                                text = if (!pincode.isNullOrEmpty()) "$name , $pincode" else name ?: "Name",
                                                color = Color(0xFF6188A0),
                                                fontFamily = fontInter,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 15.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )

                                            Text(
                                                text = address ?: "Address : ",
                                                color = Color(0xFF6188A0),
                                                fontFamily = fontInter,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 15.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            Button(
                                                modifier = Modifier
                                                    .width(90.dp),
                                                onClick = {
                                                    initiallyOpened = true
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = tintGreen
                                                ),
                                                shape = RoundedCornerShape(15.dp),
                                                contentPadding = PaddingValues(
                                                    horizontal = 20.dp,
                                                    vertical = 3.dp
                                                ),
                                                border = BorderStroke(
                                                    width = 2.dp,
                                                    color = Color(0x66676767)
                                                )
                                            ) {

                                                Text(
                                                    text = "Change",
                                                    color = tintGrey,
                                                    fontSize = 13.sp
                                                )
                                            }
                                        }
                                    }
                                }
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(3.dp)
                                        .background(appbackgroundcolor)
                                )
                            }

                            items(products.size) { index ->
                                val product = products[index]

                                CartItemCard(
                                    index = index ,
                                    cartIItem = product,
                                    context = context ,
                                    navController = navController ,
                                    selectedQuantities = selectedQuantities,
                                    onQuantityChanged = {
                                        selectedQuantities[index] = it
                                        val (newTotalCurrent, newTotalOg) = recalculateTotal(products, selectedQuantities)
                                        totalCurrentPrice = newTotalCurrent
                                        totalOgPrice = newTotalOg
                                    }
                                )
                            }

                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .background(appbackgroundcolor)
                                        .padding(
                                            start = 20.dp,
                                            end = 30.dp
                                        )
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "No products added to cart",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                is Resource.Error -> {
                    // Show error message
                    Text(
                        text = (cartState as Resource.Error).errorMassage ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Log.e("CartScreen", "Error loading products: ${cartState.errorMassage}")
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
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(appbackgroundcolor)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(
                                start = 23.dp,
                                end = 23.dp,
                                bottom = 2.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "₹${numberFormat.format(totalOgPrice)}",
                                color = tintGrey,
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 12.sp
                            )
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "₹${numberFormat.format(totalCurrentPrice)}",
                                color = textcolorblue,
                                fontSize = 20.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            BlueButton(
                                width_fraction = 1f,
                                button_text = "Place Order",
                                font_Family = fontBaloo,
                                enabled = paymentState !is PaymentState.Processing ,
                                onClick = {
                                    if(totalCurrentPrice != 0.0 && totalCurrentPrice.toFloat() > 0){
                                        paymentViewModel.initiatePayment(context as Activity, totalCurrentPrice.toString())
                                    }
                                    else{
                                        customToast(context,"Please enter a valid amount",true)
                                    }
                                }
                            )
                            LaunchedEffect(paymentState) {
                                when(paymentState){
                                    is PaymentState.Failure ->{
                                        paymentViewModel.resetPaymentState()
                                    }
                                    is PaymentState.Success ->{
                                        paymentViewModel.resetPaymentState()
                                    }
                                    else->{}
                                }
                            }
                            Text(if (paymentState is PaymentState.Processing) "" else "")
                            when(paymentState){
                                is PaymentState.Failure ->  customToast(context,"Payment Failed: ${(paymentState as PaymentState.Failure).errorMessage}",true)
                                PaymentState.Idle -> {
                                    // No status to show initially
                                }

                                is PaymentState.Success ->  customToast(context,"Payment Successful: ${(paymentState as PaymentState.Success).paymentId}",true)
                                else -> {}
                            }
                        }
                    }

                }
            }
        }
        Address_dialog(
            navController = navController,
            initiallyOpened = initiallyOpened,
            onDismissRequest = { initiallyOpened = false } ,
            context = context
        )

        Card(
            modifier = Modifier
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
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.navigate(Screen.Profile_Screen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "My Cart",
                        fontSize = 23.sp,
                        color = Color.Black,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
        }
    }
}

fun recalculateTotal2(
    cartList: List<CartIItem>,
    selectedQuantities: Map<Int, Int>
): Pair<Double, Double> {
    var totalCurrentPrice = 0.0
    var totalOgPrice = 0.0

    selectedQuantities.forEach { (key, selectedQty) ->
        val item = cartList.getOrNull(key)
        if (item != null) {
            totalCurrentPrice += item.productItem.currprice * selectedQty
            totalOgPrice += item.productItem.ogprice * selectedQty
        }
    }

    return Pair(totalCurrentPrice, totalOgPrice)
}

@Composable
fun CartItemCard2(
    index : Int,
    cartIItem: CartIItem,
    navController: NavController,
    context: Context ,
    selectedQuantities: MutableMap<Int, Int>,
    onQuantityChanged: (Int) -> Unit
) {

    val imageItemJson = Gson().toJson(cartIItem.productItem)
    val encodedProductItem = URLEncoder.encode(imageItemJson, "UTF-8")

    val item = cartIItem.productItem

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {

        val minQty = item.minQuantity
        val maxQty = item.maxQuantity

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(
                    top = 40.dp,
                    start = 20.dp
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {

                    item.remoteImageUrl?.let { imageUrl ->
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(imageUrl)
                                .crossfade(true)
                                .error(R.drawable.retail)
                                .placeholder(R.drawable.custom)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .width(115.dp)
                                .height(115.dp)
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

                    Spacer(modifier = Modifier.height(5.dp))

                    val quantity = (minQty..maxQty).map { it.toString() }

                    var selectedqty by rememberSaveable { mutableStateOf(selectedQuantities[index]?.toString() ?: "$minQty") }

//                    var selectedqty by rememberSaveable {
//                        mutableStateOf("$minQty")
//                    }
                    Spinner(
                        modifier = Modifier,
                        itemList = quantity,
                        selectedItem = selectedqty,
                        onItemSelected = {
                            selectedqty = it
                            onQuantityChanged(it.toInt())
                        },
                        spinnerwidth = 115.dp
                    )

                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = 5.dp,
                                vertical = 2.dp
                            ),
                        text = item.name,
                        color = textcolorblue,
                        maxLines = 1
                    )

                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            painter = painterResource(id = R.drawable.rounded_arrow_downward_24),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        val percentless = percentLess(
                            item.ogprice,
                            item.currprice
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 2.dp),
                            text = "${percentless}%",
                            color = rateboxGreen
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 5.dp,
                                vertical = 2.dp
                            ),
                            text = "₹${item.ogprice}",
                            color = tintGrey,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            modifier = Modifier.padding(
                                horizontal = 5.dp,
                                vertical = 2.dp
                            ),
                            text = "₹${item.currprice}",
                            color = textcolorblue
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .width(65.dp)
                            .height(25.dp)
                            .padding(horizontal = 5.dp)
                            .clip(
                                RoundedCornerShape(15.dp)
                            )
                            .background(rateboxGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.rating.toString(),
                                color = Color.White,
                                fontFamily = fontBaloo,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Image(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                painter = painterResource(id = R.drawable.rounded_star_half_24),
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            Row {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(
                            horizontal = 5.dp,
                            vertical = 2.dp
                        ),
                    text = "Delivery by Jan 12 , Fri ",
                    color = tintGrey,
                    maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(
                            horizontal = 5.dp,
                            vertical = 2.dp
                        ),
                    text = "FREE",
                    color = topbardarkblue,
                    maxLines = 1
                )
            }
        }

        if(minQty != 1){
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(appbackgroundcolor)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(tintGreen)
                    .padding(horizontal = 25.dp),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = "Minimum Order Quantity :  $minQty " ,
                    color = textcolorgrey
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
        }
        else{
            Spacer(modifier = Modifier.height(20.dp))
        }

        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .weight(1f)
                    .drawBehind {
                        drawLine(
                            color = appbackgroundcolor,
                            start = Offset(
                                0f,
                                0f
                            ),
                            end = Offset(
                                0f,
                                size.height
                            ),
                            strokeWidth = 6f
                        )
                        drawLine(
                            color = appbackgroundcolor,
                            start = Offset(
                                0f,
                                size.height
                            ),
                            end = Offset(
                                size.width,
                                size.height
                            ),
                            strokeWidth = 6f
                        )
                        drawLine(
                            color = appbackgroundcolor,
                            start = Offset(
                                size.width,
                                0f
                            ),
                            end = Offset(
                                size.width,
                                size.height - 15f
                            ),
                            strokeWidth = 6f
                        )
                        drawLine(
                            color = appbackgroundcolor,
                            end = Offset(
                                0f,
                                0f
                            ),
                            start = Offset(
                                size.width,
                                0f
                            ),
                            strokeWidth = 6f
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .size(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rounded_delete_24),
                            contentDescription = "wishlist icon"
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Remove",
                        color = tintGrey
                    )

                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .weight(1f)
                    .drawBehind {
                        drawLine(
                            color = appbackgroundcolor,
                            start = Offset(
                                0f,
                                0f
                            ),
                            end = Offset(
                                0f,
                                size.height - 15f
                            ),
                            strokeWidth = 6f
                        )
                        drawLine(
                            color = appbackgroundcolor,
                            start = Offset(
                                0f,
                                size.height
                            ),
                            end = Offset(
                                size.width,
                                size.height
                            ),
                            strokeWidth = 6f
                        )
                        drawLine(
                            color = appbackgroundcolor,
                            start = Offset(
                                size.width,
                                0f
                            ),
                            end = Offset(
                                size.width,
                                size.height
                            ),
                            strokeWidth = 6f
                        )
                        drawLine(
                            color = appbackgroundcolor,
                            end = Offset(
                                0f,
                                0f
                            ),
                            start = Offset(
                                size.width,
                                0f
                            ),
                            strokeWidth = 6f
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .size(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                            contentDescription = "wishlist icon"
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Buy Now",
                        color = tintGrey
                    )

                }
            }
        }


        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .background(appbackgroundcolor)
        )
    }
}
