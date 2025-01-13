package com.example.vsgarments.layout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.vsgarments.R
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.dataStates.ProductItem
import com.example.vsgarments.dataStates.SizePrice
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.product.ProductViewModel
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.ui.theme.topbarlightblue

const val IMAGE_PICK_REQUEST_CODE = 1001

@Composable
fun ProductScreen(
    navController: NavController,
    modifier: Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val productViewModel: ProductViewModel = hiltViewModel()
    val productState by productViewModel.productState.collectAsState()

    val productName = remember { mutableStateOf("") }
    val companyName = remember { mutableStateOf("") }
    val currPrice = remember { mutableStateOf("") }
    val ogPrice = remember { mutableStateOf("") }
    val rating = remember { mutableStateOf("") }
    val minQuantity = remember { mutableStateOf("") }
    val maxQuantity = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val inStock = remember { mutableStateOf(true) }
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    // Use SnapshotStateMap instead of mutableMapOf
    val sizeToPriceMap = remember { mutableStateMapOf<String, SizePrice>() }
    val sizeToStockMap = remember { mutableStateMapOf<String, Boolean>() }

    // Temporary state variables for adding size-specific details
    val sizeInput = remember { mutableStateOf("") }
    val sizeCurrPrice = remember { mutableStateOf("") }
    val sizeOgPrice = remember { mutableStateOf("") }
    val sizeInStock = remember { mutableStateOf(true) }

    // Image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            imageUri.value = uri
            Toast.makeText(context, "Image selected successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Image selection canceled", Toast.LENGTH_SHORT).show()
        }
    }

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
                    vertical = 30.dp
                )
                .fillMaxSize()
                .verticalScroll(productScroll),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {

            Text(
                text = "Add New Product",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Input Fields
            OutlinedTextField(
                value = productName.value,
                onValueChange = { productName.value = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = companyName.value,
                onValueChange = { companyName.value = it },
                label = { Text("Company Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = currPrice.value,
                onValueChange = { currPrice.value = it },
                label = { Text("Current Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = ogPrice.value,
                onValueChange = { ogPrice.value = it },
                label = { Text("Original Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = minQuantity.value,
                onValueChange = { minQuantity.value = it },
                label = { Text("Minimum Quantity") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = maxQuantity.value,
                onValueChange = { maxQuantity.value = it },
                label = { Text("Maximum Quantity") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            // In-stock Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = inStock.value,
                    onCheckedChange = { inStock.value = it }
                )
                Text(text = "In Stock")
            }

            Text(text = "Add Size Details")

            OutlinedTextField(
                value = sizeInput.value,
                onValueChange = { sizeInput.value = it },
                label = { Text("Size") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = sizeCurrPrice.value,
                onValueChange = { sizeCurrPrice.value = it },
                label = { Text("Current Price for Size") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = sizeOgPrice.value,
                onValueChange = { sizeOgPrice.value = it },
                label = { Text("Original Price for Size") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = sizeInStock.value,
                    onCheckedChange = { sizeInStock.value = it }
                )
                Text(text = "In Stock for Size")
            }

            Button(
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Size Details")
            }

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Select Product Image")
            }

            imageUri.value?.let { uri ->
                Text(text = "Image Selected: ${uri.lastPathSegment}")
            }

//            Button(
//                onClick = {
//                    val intent = Intent(Intent.ACTION_PICK).apply {
//                        type = "image/*"
//                    }
//                    val activity = context as Activity
//                    activity.startActivityForResult(intent, IMAGE_PICK_REQUEST_CODE)
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Select Product Image")
//            }
//
//            imageUri.value?.let { uri ->
//                Text(text = "Image Selected: ${uri.lastPathSegment}")
//            }


            // Add Product Button
            Button(
                onClick = {

                    if (productName.value.isBlank() || companyName.value.isBlank()) {
                        Toast.makeText(context, "Product name and company name are required", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (currPrice.value.isBlank() || currPrice.value.toIntOrNull() == null) {
                        Toast.makeText(context, "Invalid current price", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (ogPrice.value.isBlank() || ogPrice.value.toIntOrNull() == null) {
                        Toast.makeText(context, "Invalid original price", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val productItem = ProductItem(
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
                        localImageUri = imageUri.value
                    )

                    productViewModel.addProduct(productItem)

                    Toast.makeText(context, "Product added successfully!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Product")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Product List")

            when (productState) {
                is Resource.Loading -> {
                    // Show a loading indicator
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is Resource.Success -> {
                    // Safely handle the products list, which could be null
                    val products = (productState as Resource.Success<List<ProductItem>>).data

                    // Check if the list is not null and has items
                    if (!products.isNullOrEmpty()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                                .height(600.dp)
                        ) {
                            items(products.size) { index ->
                                val product = products[index]
                                ProductItemCard(product)
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
                        text = (productState as Resource.Error).errorMassage ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Log.e("ProductScreen", "Error loading products: ${productState.errorMassage}")
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
                                navController.navigate(Screen.Profile_Screen.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Product Details",
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

@Composable
fun ProductItemCard(product: ProductItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
            )
            Text(text = "Company: ${product.CompanyName}")
            Text(text = "Price: \$${product.currprice} (Original: \$${product.ogprice})")
            Text(text = "Rating: ${product.rating}")
            Text(text = "Min Quantity: ${product.minQuantity}")
            Text(text = "Max Quantity: ${product.maxQuantity}")
            Text(text = "Description: ${product.description}")
            Text(text = "In Stock: ${if (product.inStock) "Yes" else "No"}")

            Spacer(modifier = Modifier.height(8.dp))

            // Display Sizes and their prices/stock
            Text(text = "Available Sizes:")
            product.sizeToPriceMap.forEach { (size, price) ->
                val (currPrice, ogPrice) = price
                Text(text = "$size: Price - \$${currPrice} / \$${ogPrice}, In Stock: ${if (product.sizeToStockMap[size] == true) "Yes" else "No"}")
            }
        }
    }
}