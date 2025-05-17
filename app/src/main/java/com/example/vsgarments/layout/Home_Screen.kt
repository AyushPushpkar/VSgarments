package com.example.vsgarments.layout

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.identity.documenttype.Icon
import com.example.vsgarments.R
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.navigation.Screen
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.fontInter
import com.example.vsgarments.ui.theme.grey
import com.example.vsgarments.ui.theme.lightblack
import com.example.vsgarments.ui.theme.topbardarkblue
import com.example.vsgarments.view_functions.AppTopBar
import com.example.vsgarments.dataStates.ProductItem
import com.example.vsgarments.product.ProductViewModel
import com.example.vsgarments.ui.theme.fontBaloo
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey
import com.example.vsgarments.ui.theme.tintGrey
import com.example.vsgarments.ui.theme.topbarlightblue
import com.example.vsgarments.view_functions.SearchBar
import com.example.vsgarments.view_functions.char_editText
import com.example.vsgarments.view_functions.customToast
import com.example.vsgarments.view_functions.text_textField
import com.example.vsgarments.wishlist.LikeViewModel
import com.example.vsgarments.wishlist.WishlistItem
import com.example.vsgarments.wishlist.WishlistViewModel
import com.google.gson.Gson
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import java.net.URLEncoder
@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appbackgroundcolor)

    ) {

        val focusManager = LocalFocusManager.current
        val focusRequester = FocusRequester()

        var searchQuery by remember { mutableStateOf("") }
        var bestDealsOnly by remember { mutableStateOf(false) }

        val context = LocalContext.current
        val productViewModel: ProductViewModel = hiltViewModel()
        val productState by productViewModel.productState.collectAsState()
        val filteredProducts by productViewModel.filteredProductState.collectAsState()



        var isFilteringApplied by rememberSaveable { mutableStateOf(false) }

        val keyboardController = LocalSoftwareKeyboardController.current

        Column (
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
        ) {

            AppTopBar(navController = navController , context = context)

            when (productState) {
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
                                chunk.forEach { _ ->
                                    ShimmerLoadingEffect()
                                }
                            }
                        }
                    }

                }
                is Resource.Success -> {
//                    if(filteredProducts is Resource.Success && searchQuery.isNotEmpty()) isFilteringApplied = true
                    val products = if (isFilteringApplied) {
                        (filteredProducts as? Resource.Success<List<ProductItem>>)?.data ?: emptyList() // Use filtered data if available
                    } else {
                        (productState as? Resource.Success<List<ProductItem>>)?.data ?: emptyList()
                    }

                    if (products.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            val productChunks = products.chunked(2)
                            items(productChunks.size) { chunkIndex ->
                                val chunk = productChunks[chunkIndex]

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    chunk.forEach { product ->
                                        HomeItemCard(
                                            product = product,
                                            context = context ,
                                            navController = navController
                                        )
                                    }
                                }
                            }

                            item {
                                Spacer(modifier = Modifier
                                    .height(75.dp)
                                    .background(appbackgroundcolor))
                            }
                        }
                    } else if (isFilteringApplied) {
                        // Filtering was applied, but no results found
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No match found",
                                color = Color.Gray,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }else {
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
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(horizontal = 30.dp)
        ){

            Card(
                shape = RoundedCornerShape(50.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(50.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(5.dp))

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(topbardarkblue)
                    ) {
                        IconButton(
                            onClick = {
                                searchQuery.ifEmpty { null }?.let {
                                    productViewModel.searchProducts(
                                        keyword = it
                                    )
                                }
                                if (searchQuery != "")isFilteringApplied = true

                                keyboardController?.hide()
                            },
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    }

                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search products...") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                searchQuery.ifEmpty { null }?.let {
                                    productViewModel.searchProducts(
                                        keyword = it
                                    )
                                }
                                isFilteringApplied = true
                                keyboardController?.hide() // Hide keyboard when search is triggered
                            }
                        ),
                        modifier = Modifier.weight(1f)
                            .focusRequester(focusRequester)
                            .clip(RoundedCornerShape(bottomEnd = 50.dp, topEnd = 50.dp)),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            focusedLabelColor = appbackgroundcolor,
                            focusedTextColor = textcolorgrey,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedLabelColor = textcolorblue,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.White,
                            unfocusedTextColor = textcolorgrey,
                            disabledTextColor = textcolorgrey,
                            disabledLabelColor = textcolorblue,
                            disabledIndicatorColor = Color.Transparent,
                            disabledContainerColor = Color.White,
                            cursorColor = textcolorblue,
                        ),
                    )

                    if (searchQuery.isNotEmpty() || isFilteringApplied) {
                        IconButton(
                            onClick = {
                                searchQuery = ""
                                productViewModel.clearSearch() // Reset to normal products
                                isFilteringApplied = false
                                keyboardController?.hide()
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Clear search",
                                tint = Color.Gray
                            )
                        }
                    }


                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }



    }
}

@Composable
fun HeartCheckBox(context: Context,uid: String , wishlistItem: WishlistItem){
    var isLiked by remember {
        mutableStateOf(false)
    }

    val wishlistViewModel: WishlistViewModel = hiltViewModel()
    val likeViewModel : LikeViewModel = hiltViewModel()

    val wishlistResource by wishlistViewModel.wishlistProducts.collectAsState()

    val wishlist = (wishlistResource as? Resource.Success)?.data ?: emptyList()

    isLiked = wishlist.contains(wishlistItem)

    val state by animateFloatAsState(
        targetValue = if(isLiked) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )

    val color = if(isLiked) topbardarkblue else Color.Gray

    Column(modifier = Modifier
        .clickable {
            if (!isLiked){
                likeViewModel.addProductInWishlist(wishlistItem)
                customToast(context , "Added to Wishlist", cancelable = true)
            }else{
                wishlistViewModel.removeFromWishlist(wishlistItem)
                customToast(context , "Removed from Wishlist" , cancelable = true)
            }
            isLiked = !isLiked
        }
        .scale(state)) {
        Image(
            painter = painterResource(R.drawable.heart),
            contentDescription = "Heart",
            colorFilter = ColorFilter.tint(color)
        )
    }
}

@Composable
fun HomeItemCard(
    product: ProductItem ,
    navController: NavController ,
    context: Context
) {

    val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp

    val imageItemJson = Gson().toJson(product)
    val encodedProductItem = URLEncoder.encode(imageItemJson, "UTF-8")

    val wishlistViewModel: WishlistViewModel = hiltViewModel()

    Column(
            modifier = Modifier
                .width(screenwidth / 2)
                .padding(10.dp)
        ) {

            product.remoteImageUrl?.let { imageUrl ->
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(true)
                        .error(R.drawable.white_back)
                        .placeholder(R.drawable.blue_back)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(278.dp)
                        .clip(shape = RoundedCornerShape(10))
                        .clickable {
                            navController.navigate("${Screen.DisplayScreen.route}/$encodedProductItem")
                        } ,
                    contentScale = ContentScale.FillBounds ,
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

            Row {
                Column(modifier = Modifier
                    .fillMaxSize(.85f)) {
                    Text(
                        text = product.CompanyName,
                        color = lightblack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontInter
                    )
                    Text(
                        text = product.name,
                        color = grey,
                        fontSize = 14.sp,
                        fontFamily = fontInter,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .offset(y = -(3).dp)
                    )
                    Row{
                        Text(
                            text = "₹${product.ogprice}",
                            color = grey,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontInter,
                            textDecoration = TextDecoration.LineThrough,
                            maxLines = 1,
                            modifier = Modifier
                                .offset(y = -(5).dp)
                        )
                        Spacer(modifier = Modifier
                            .width(5.dp))
                        Text(
                            text = "₹${product.currprice}",
                            color = lightblack,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontInter,
                            overflow = TextOverflow.Clip,
                            maxLines = 1,
                            modifier = Modifier
                                .offset(y = -(5).dp)
                        )
                    }
                    Box(modifier = Modifier
                        .height(35.dp)
                        .padding(vertical = 3.dp)
                        .width(80.dp)
                        .offset(y = -(3.dp))
                        .clip(
                            object : Shape {
                                override fun createOutline(
                                    size: Size,
                                    layoutDirection: LayoutDirection,
                                    density: Density
                                ): Outline {
                                    val path = Path().apply {
                                        moveTo(
                                            x = 0f,
                                            y = 0f
                                        )
                                        lineTo(
                                            x = size.width,
                                            y = 0f
                                        )
                                        lineTo(
                                            x = (size.width * .9f),
                                            y = size.height
                                        )
                                        lineTo(
                                            x = 0f,
                                            y = size.height
                                        )
                                        lineTo(
                                            x = 0f,
                                            y = 0f
                                        )
                                        close()
                                    }
                                    return Outline.Generic(path)
                                }
                            }
                        )
                        .background(topbarlightblue)){
                        val pl= percentLess(product.ogprice , product.currprice);
                        Text(
                            text = "${pl}% off",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Clip,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(top = 2.dp, start = 5.dp)

                        )
                    }
                }

                val wishlistItem by remember {
                    derivedStateOf {
                        WishlistItem(
                            productItem = product
                        )
                    }
                }
                HeartCheckBox(
                    context = context,
                    uid = product.id ,
                    wishlistItem = wishlistItem
                )
            }
        }
}

@Composable
fun ShimmerLoadingEffect() {

    val screenwidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    Column(
        modifier = Modifier
            .width(screenwidth / 2)
            .padding(10.dp)
            .shimmer(shimmerInstance)
    ) {

        Box(
            modifier = Modifier
                .height(278.dp)
                .fillMaxWidth()
                .background(
                    tintGrey.copy(alpha = 0.7f),
                    RoundedCornerShape(10.dp)
                )
                .clip(shape = RoundedCornerShape(10))
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Column(
                modifier = Modifier
                    .fillMaxSize(.85f)
            ) {
                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(100.dp)
                        .background(
                            tintGrey.copy(alpha = 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                )
                Spacer(modifier = Modifier.height(5.dp))

                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(100.dp)
                        .background(
                            tintGrey.copy(alpha = 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row {
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(80.dp)
                            .background(
                                tintGrey.copy(alpha = 0.5f),
                                RoundedCornerShape(8.dp)
                            )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(5.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(80.dp)
                            .background(
                                tintGrey.copy(alpha = 0.5f),
                                RoundedCornerShape(8.dp)
                            )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(80.dp)
                        .background(
                            tintGrey.copy(alpha = 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                )
            }
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        tintGrey.copy(alpha = 0.5f),
                        RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}



