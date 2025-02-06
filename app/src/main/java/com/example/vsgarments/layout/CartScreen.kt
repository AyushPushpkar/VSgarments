package com.example.vsgarments.layout

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vsgarments.R
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
import com.example.vsgarments.dataStates.ImageItem
import com.example.vsgarments.dataStates.generateRandomAttributes
import com.example.vsgarments.dataStates.generateRandomSizeToPriceMap
import com.example.vsgarments.dataStates.generateRandomSizeToStockMap
import com.example.vsgarments.payment_methods.PaymentState
import com.example.vsgarments.payment_methods.PaymentViewModel
import com.example.vsgarments.view_functions.RadioButtons
import com.example.vsgarments.view_functions.Spinner
import com.example.vsgarments.view_functions.ToggleableInfo
import com.example.vsgarments.view_functions.blue_Button
import com.example.vsgarments.view_functions.customToast
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.net.URLEncoder

@Composable
fun CartScreen(
    modifier: Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    val paymentViewModel :  PaymentViewModel = hiltViewModel()
    val paymentState by paymentViewModel.paymentStatus.collectAsState()


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
        val coroutineScope = rememberCoroutineScope()

        fun recalculateTotal(cartList: List<ImageItem>, selectedQuantities: Map<Int, Int>) {
            coroutineScope.launch {
                totalCurrentPrice = 0.0
                totalOgPrice = 0.0

                selectedQuantities.forEach { (key, selectedQty) ->
                    val item = cartList[key]
                    totalCurrentPrice += item.currprice * selectedQty
                    totalOgPrice += item.ogprice * selectedQty
                }
            }
        }

        LaunchedEffect(Unit) {
            cartList.forEachIndexed { index, item ->
                selectedQuantities[index] = item.minQuantity
            }
            recalculateTotal(cartList, selectedQuantities)
        }

        Column(
            modifier = Modifier
                .background(appbackgroundcolor)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
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


                itemsIndexed(cartList) { index, item ->

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
                                    val imageItemJson = Gson().toJson(item)
                                    val encodedImageItem = URLEncoder.encode(imageItemJson, "UTF-8")
                                    Image(
                                        painter = painterResource(id = item.imageresId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(115.dp)
                                            .height(115.dp)
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            )
                                            .clickable {
                                                navController.navigate("${Screen.DisplayScreen.route}/$encodedImageItem")
                                            },
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(modifier = Modifier.height(5.dp))

                                    val quantity = (minQty..maxQty).map { it.toString() }

                                    var selectedqty by rememberSaveable {
                                        mutableStateOf("$minQty")
                                    }
                                    Spinner(
                                        modifier = Modifier,
                                        itemList = quantity,
                                        selectedItem = selectedqty,
                                        onItemSelected = {
                                            selectedqty = it
                                            selectedQuantities[index] = it.toInt()
                                            recalculateTotal(
                                                cartList,
                                                selectedQuantities
                                            )
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

                        Column() {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "₹${totalOgPrice}",
                                color = tintGrey,
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 12.sp
                            )
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                ),
                                text = "₹${totalCurrentPrice}",
                                color = textcolorblue,
                                fontSize = 20.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            blue_Button(
                                width_fraction = 0.5f,
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

private val cartList = listOf(
    ImageItem(
        imageresId = R.drawable.retail,
        currprice = 300,
        ogprice = 400,
        name = "Kipo and the Age of Wonderbeasts",
        CompanyName = "The VS Garments",
        rating = 4.0f,
        minQuantity = 1,
        maxQuantity = 20,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        orderDate ="",
        orderId = "",
        orderType = "",
        supplier = "",
        size = "",
        inStock = false,
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
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
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        orderDate ="",
        orderId = "",
        orderType = "",
        supplier = "",
        size = "",
        inStock = false,
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply { updatePriceBasedOnSize("S")},
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
        orderDate ="",
        orderId = "",
        orderType = "",
        supplier = "",
        size = "",
        inStock = false,
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply { updatePriceBasedOnSize("S")},
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
        orderDate ="",
        orderId = "",
        orderType = "",
        supplier = "",
        size = "",
        inStock = false,
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply { updatePriceBasedOnSize("S")},
    ImageItem(
        imageresId = R.drawable.bulk_order,
        currprice = 360,
        ogprice = 460,
        name = "Luna",
        CompanyName = "The VS Garments",
        rating = 4.3f,
        minQuantity = 4,
        maxQuantity = 200,
        sizeToPriceMap = generateRandomSizeToPriceMap(),
        orderDate ="",
        orderId = "",
        orderType = "",
        supplier = "",
        size = "",
        inStock = false,
        sizeToStockMap = generateRandomSizeToStockMap(),
        productDetails = generateRandomAttributes() ,
        description = ""
    ).apply { updatePriceBasedOnSize("S")},
)

@Composable
fun Address_dialog(
    navController: NavController,
    modifier: Modifier = Modifier,
    initiallyOpened: Boolean,
    onDismissRequest: () -> Unit,
    context: Context
) {

    AnimatedVisibility(
        visible = initiallyOpened,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x4DB6E9FF))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(213.dp)
                    .background(Color.Transparent)
                    .clickable { onDismissRequest() }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .background(Color.White)
                    .padding(35.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {}  // Consume taps
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { _, _ -> /* Consume drag gestures */ }
                    },
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .height(0.dp)
                )

                Row {
                    Text(
                        modifier = Modifier.weight(9f),
                        text = "Select Delivery Address ",
                        color = textcolorgrey,
                        fontSize = 20.sp,
                        fontFamily = fontInter,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onDismissRequest()
                            },
                        painter = painterResource(id = R.drawable.edit_pen),
                        contentDescription = "cross icon"
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                val options = listOf(
                    ToggleableInfo(false, "Option 1" , "fjba b w wvwqiv twv w4j jt 34j joov  ", "800054"),
                    ToggleableInfo(false, "Option 2" , "gvrvnwc uy wrrg uurg g3412t3fvhrhrq ug 2ug httw t" , "844545"),
                    ToggleableInfo(false, "Option 3" , "ayushs khhrv wtt hti hyi jt jyoyu" , "235224")
                )

                val savedOption = getSavedAddressOption(context)
                val defaultOption = options.find {
                    it.addresstext == savedOption.address &&
                            it.nametext == savedOption.name &&
                            it.pincode == savedOption.pincode
                } ?: options.firstOrNull()

                var selectedOption by remember { mutableStateOf(defaultOption) }


                RadioButtons(
                    options = options,
                    selectedOption = selectedOption,
                    onOptionSelected = { selected ->
                        selectedOption = selected
                        saveAddressOption(
                            context,
                            selected.addresstext,
                            selected.nametext,
                            selected.pincode
                        )
                        onDismissRequest()
                    }
                )


            }
        }
    }
}

fun saveAddressOption(context: Context, addressText: String , nameText : String , pincode : String) {
    val sharedPreferences = context.getSharedPreferences("AddressPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("AddressOption", addressText)
        putString("NameOption", nameText)
        putString("PincodeOption", pincode)
        apply()
    }
}

fun getSavedAddressOption(context: Context): AddressInfo {
    val sharedPreferences = context.getSharedPreferences("AddressPrefs", Context.MODE_PRIVATE)
    val address = sharedPreferences.getString("AddressOption", null) ?: ""
    val name = sharedPreferences.getString("NameOption", null) ?: "Address : "
    val pincode = sharedPreferences.getString("PincodeOption", null) ?: ""
    return AddressInfo(address, name, pincode)
}

