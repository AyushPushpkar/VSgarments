package com.example.vsgarments.layout

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.vsgarments.R
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.dataStates.ProductItem
import com.example.vsgarments.dataStates.SizePrice
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.product.ProductViewModel
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.tintGreen
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.CheckBoxWithText
import com.example.vsgarments.view_functions.BlueButton
import com.example.vsgarments.view_functions.char_editText
import com.example.vsgarments.view_functions.customToast
import com.example.vsgarments.view_functions.number_editText
import java.util.UUID

@Composable
fun UpdateProductScreen(
    navController: NavController,
    modifier: Modifier ,
    productId : String?
) {



    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val productViewModel: ProductViewModel = hiltViewModel()
    val productState by productViewModel.updateProductState.collectAsState()

    val productName = remember { mutableStateOf("") }
    val companyName = remember { mutableStateOf("") }
    val currPrice = remember { mutableStateOf("") }
    val ogPrice = remember { mutableStateOf("") }
    val rating = remember { mutableStateOf("") }
    val minQuantity = remember { mutableStateOf("") }
    val maxQuantity = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val inStock = remember { mutableStateOf(true) }

    val remoteImageUrl = remember { mutableStateOf<String?>(null) }  // New remote image URL state
    val localImageUri = remember { mutableStateOf<Uri?>(null) }  // New local image URI state

    val sizeToPriceMap = remember { mutableStateMapOf<String, SizePrice>() }
    val sizeToStockMap = remember { mutableStateMapOf<String, Boolean>() }

    LaunchedEffect(Unit) {
        productId?.let {
            productViewModel.fetchProductById(it) { product ->
                product?.let { item ->
                    productName.value = item.name
                    companyName.value = item.CompanyName
                    currPrice.value = item.currprice.toString()
                    ogPrice.value = item.ogprice.toString()
                    rating.value = item.rating.toString()
                    minQuantity.value = item.minQuantity.toString()
                    maxQuantity.value = item.maxQuantity.toString()
                    description.value = item.description
                    inStock.value = item.inStock
                    remoteImageUrl.value = item.remoteImageUrl
                    sizeToPriceMap.clear()
                    sizeToPriceMap.putAll(item.sizeToPriceMap)
                    sizeToStockMap.clear()
                    sizeToStockMap.putAll(item.sizeToStockMap)
                }
            }
        }
    }

    val sizeInput = remember { mutableStateOf("") }
    val sizeCurrPrice = remember { mutableStateOf("") }
    val sizeOgPrice = remember { mutableStateOf("") }
    val sizeInStock = remember { mutableStateOf(true) }

    // Image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            localImageUri.value = uri
            Toast.makeText(context, "Image selected successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Image selection canceled", Toast.LENGTH_SHORT).show()
        }
    }

    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    var buttontext by remember { mutableStateOf("Wait ✋") }

    Box (
        modifier = modifier
            .fillMaxSize()
            .background(appbackgroundcolor)
    ){

        val productScroll = rememberScrollState()
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(
                    horizontal = 50.dp,
                )
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .verticalScroll(productScroll),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Update Product",
                modifier = Modifier.align(Alignment.CenterHorizontally) ,
                color = tintGrey
            )

            // Input Fields
            char_editText(
                text = productName.value,
                onTextChange = { productName.value = it },
                hint = "Product Name",
                modifier = Modifier.fillMaxWidth() ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            char_editText(
                text = companyName.value,
                onTextChange = { companyName.value = it },
                hint = "Company Name",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            number_editText(
                text = currPrice.value,
                onTextChange = { currPrice.value = it },
                hint = "Current Price",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            number_editText(
                text = ogPrice.value,
                onTextChange = { ogPrice.value = it },
                hint = "Original Price",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            number_editText(
                text = minQuantity.value,
                onTextChange = { minQuantity.value = it },
                hint = "Minimum Quantity",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            number_editText(
                text = maxQuantity.value,
                onTextChange = { maxQuantity.value = it },
                hint = "Maximum Quantity",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            char_editText(
                text = description.value ,
                onTextChange = { description.value = it },
                hint = "Description" ,
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            CheckBoxWithText(
                isChecked = inStock.value,
                onCheckedChange = {inStock.value = it} ,
                text = "In Stock"
            )

            Text(text = "Add Size Details" , color = tintGrey)

            char_editText(
                text = sizeInput.value,
                onTextChange = { sizeInput.value = it },
                hint = "Size",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            number_editText(
                text = sizeCurrPrice.value,
                onTextChange = { sizeCurrPrice.value = it },
                hint = "Current Price",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            number_editText(
                text = sizeOgPrice.value,
                onTextChange = { sizeOgPrice.value = it },
                hint = "Original Price",
                modifier = Modifier.fillMaxWidth()  ,
                font_Family = fontInter ,
                focusRequester = focusRequester
            )

            CheckBoxWithText(
                isChecked = sizeInStock.value,
                onCheckedChange = {sizeInStock.value = it},
                text = "In Stock for Size"
            )

            BlueButton(
                onClick = {
                    val size = sizeInput.value
                    val currPriceVal = sizeCurrPrice.value.toIntOrNull()
                    val ogPriceVal = sizeOgPrice.value.toIntOrNull()
                    if (size.isNotBlank() && currPriceVal != null && ogPriceVal != null) {
                        sizeToPriceMap[size] = SizePrice(currPriceVal, ogPriceVal)
                        sizeToStockMap[size] = sizeInStock.value
                        sizeInput.value = ""
                        sizeCurrPrice.value = ""
                        sizeOgPrice.value = ""
                        sizeInStock.value = true
                        Toast.makeText(context, "Size details added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Invalid size details", Toast.LENGTH_SHORT).show()
                    }
                },
                width_fraction = 0.9f,
                button_text = "Add Size Details",
                font_Family = fontBaloo
            )

            Text(
                text = "Edit Size Details" ,
                color = tintGrey
            )

            sizeToPriceMap.keys.forEach { size ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .background(tintGreen)
                        .padding(8.dp)
                ) {
                    Text(text = "Size: $size", color = textcolorblue)

                    number_editText(
                        text = sizeToPriceMap[size]?.currentPrice?.toString() ?: "",
                        onTextChange = {
                            val ogPrice = sizeToPriceMap[size]?.originalPrice ?: 0
                            sizeToPriceMap[size] = SizePrice(it.toIntOrNull() ?: 0, ogPrice)
                        },
                        hint = "Current Price for $size",
                        focusRequester = focusRequester ,
                        font_Family = fontInter
                    )

                    number_editText(
                        text = sizeToPriceMap[size]?.originalPrice?.toString() ?: "",
                        onTextChange = {
                            val currPrice = sizeToPriceMap[size]?.currentPrice ?: 0
                            sizeToPriceMap[size] = SizePrice(currPrice, it.toIntOrNull() ?: 0)
                        },
                        hint = "Original Price for $size",
                        focusRequester = focusRequester ,
                        font_Family = fontInter
                    )

                    CheckBoxWithText(
                        isChecked = sizeToStockMap[size] ?: false,
                        onCheckedChange = { sizeToStockMap[size] = it },
                        text = "In Stock for $size"
                    )

                    IconButton(
                        onClick = {
                            sizeToPriceMap.remove(size)
                            sizeToStockMap.remove(size)
                            Toast.makeText(context, "Size removed", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove Size" ,
                            tint = topbardarkblue
                        )
                    }
                }
            }

            BlueButton(
                width_fraction = 0.9f,
                button_text = "Update Product Image",
                font_Family = fontBaloo
            ) {
                imagePickerLauncher.launch("image/*")
            }

            localImageUri.value?.let { uri ->
                Text(text = "Image Selected: ${uri.lastPathSegment}")
            }

            BlueButton(
                width_fraction = 0.9f,
                button_text = "Update Product",
                font_Family = fontBaloo,
                onClick = {
                    if (productName.value.isBlank() || companyName.value.isBlank()) {
                        Toast.makeText(context, "Product name and company name are required", Toast.LENGTH_SHORT).show()
                        return@BlueButton
                    }
                    if (currPrice.value.isBlank() || currPrice.value.toIntOrNull() == null) {
                        Toast.makeText(context, "Invalid current price", Toast.LENGTH_SHORT).show()
                        return@BlueButton
                    }
                    if (ogPrice.value.isBlank() || ogPrice.value.toIntOrNull() == null) {
                        Toast.makeText(context, "Invalid original price", Toast.LENGTH_SHORT).show()
                        return@BlueButton
                    }

                    val productItem = ProductItem(
                        id = productId ?: UUID.randomUUID().toString(),
                        name = productName.value,
                        CompanyName = companyName.value,
                        currprice = currPrice.value.toIntOrNull() ?: 0,
                        ogprice = ogPrice.value.toIntOrNull() ?: 0,
                        rating = rating.value.toFloatOrNull() ?: 0.0f,
                        minQuantity = minQuantity.value.toIntOrNull() ?: 1,
                        maxQuantity = maxQuantity.value.toIntOrNull() ?: 500,
                        description = description.value,
                        inStock = inStock.value,
                        sizeToPriceMap = sizeToPriceMap,
                        sizeToStockMap = sizeToStockMap ,
                        remoteImageUrl = remoteImageUrl.value,
                        localImageUri = localImageUri.value
                    )

                    if (productId != null) {
                        productViewModel.updateProduct(productId = productId , product = productItem , context)
                    }
                }
            )

            Row (
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center
            ){
                BlueButton(
                    width_fraction = 0.7f,
                    button_text = buttontext,
                    font_Family = fontBaloo
                ) {
                    navController.navigate(Screen.AddProductScreen.route)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            when (productState) {
                is Resource.Loading -> {
                    // Show a loading indicator
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is Resource.Success -> {

                    buttontext = "Go Back"

                    customToast(context, "Product added successfully!" , cancelable = true)
                }
                is Resource.Error -> {
                    Text(
                        text = (productState as Resource.Error).errorMassage ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Log.e("ProductScreen", "Error loading products: ${productState.errorMassage}")
                }
                else -> {
                }
            }

        }

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
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.navigate(Screen.AddProductScreen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Update Product ",
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