package com.example.vsgarments.layout

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.identity.documenttype.Icon
import com.example.vsgarments.R
import com.example.vsgarments.authentication.util.Resource
import com.example.vsgarments.dataStates.ProductItem
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
import com.example.vsgarments.view_functions.BlueButton
import com.google.gson.Gson
import java.net.URLEncoder
import com.example.vsgarments.product.ProductViewModel
import com.example.vsgarments.wishlist.WishlistItem
import com.example.vsgarments.wishlist.WishlistViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Wishlist(
    modifier: Modifier,
    navController: NavController,
) {
    val scrollview = rememberScrollState()
    Column(
        modifier = modifier
            .background(appbackgroundcolor)
    ) {

        val context = LocalContext.current

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
                                navController.popBackStack()
                            }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "Wishlist",
                        fontSize = 23.sp,
                        color = Color.Black,
                        fontFamily = fontBaloo,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

            }
        }

        val wishlistViewModel: WishlistViewModel = hiltViewModel()
        val wishlistState by wishlistViewModel.wishlistProducts.collectAsState()

        when (wishlistState) {
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
                val products = (wishlistState as Resource.Success<List<WishlistItem>>).data

                if (!products.isNullOrEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {

                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(75.dp)
                                    .background(tintGreen)
                                    .padding(
                                        start = 20.dp,
                                        end = 30.dp
                                    ),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                var isChecked by remember { mutableStateOf(true) }
                                Text(
                                    text = "Show in stock products only",
                                    color = Color(0xFF6188A0),
                                    fontFamily = fontInter,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Switch(
                                        modifier = Modifier
                                            .scale(0.8f),
                                        checked = isChecked,
                                        onCheckedChange = { isChecked = it },
                                        colors = SwitchColors(
                                            checkedThumbColor = tintGreen,
                                            checkedTrackColor = topbardarkblue,
                                            checkedBorderColor = Color.Transparent,
                                            checkedIconColor = Color.Red,
                                            uncheckedThumbColor = textcolorgrey,
                                            uncheckedTrackColor = topbardarkblue,
                                            uncheckedBorderColor = Color.Transparent,
                                            uncheckedIconColor = Color.Black,
                                            disabledCheckedThumbColor = Color.Black,
                                            disabledCheckedTrackColor = Color.Black,
                                            disabledCheckedBorderColor = Color.Black,
                                            disabledCheckedIconColor = Color.Black,
                                            disabledUncheckedThumbColor = Color.Black,
                                            disabledUncheckedTrackColor = Color.Black,
                                            disabledUncheckedBorderColor = Color.Black,
                                            disabledUncheckedIconColor = Color.Black
                                        ),
                                        interactionSource = remember { MutableInteractionSource() }
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                        }

                        val productChunks = products.dropLast(if (products.size % 2 != 0) 1 else 0).chunked(2)
                        val lastItem = if (products.size % 2 != 0) products.last() else null

                        items(productChunks.size) { chunkIndex ->
                            val chunk = productChunks[chunkIndex]

                            val isLastSingleItem = chunkIndex == productChunks.lastIndex && chunk.size == 1


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(appbackgroundcolor)
                                    .padding(vertical = 1.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                chunk.forEach { product ->
                                    WishlistItemCard(
                                        wishlistItem = product,
                                        context = context ,
                                        navController = navController ,
                                        onRemoveClicked = { wishlistViewModel.removeFromWishlist(product) } ,
                                    )
                                }
                            }
                        }

                        lastItem?.let { item ->
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(appbackgroundcolor)
                                        .padding(vertical = 1.dp , horizontal = 2.dp),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    WishlistItemCard(
                                        wishlistItem = item,
                                        context = context,
                                        navController = navController,
                                        onRemoveClicked = { wishlistViewModel.removeFromWishlist(item) },
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = "No products in wishlist",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxHeight()
                    )
                }
            }
            is Resource.Error -> {
                // Show error message
                Text(
                    text = (wishlistState as Resource.Error).errorMassage ?: "Unknown error",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Log.e("ProductScreen", "Error loading products: ${wishlistState.errorMassage}")
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

fun percentLess(
    ogprice: Int,
    currprice: Int
): Int {
    val percentage = ((ogprice - currprice) * 100 / ogprice).toInt()
    return percentage
}

@Composable
fun WishlistItemCard(
    modifier: Modifier = Modifier,
    wishlistItem: WishlistItem ,
    navController: NavController ,
    context: Context ,
    onRemoveClicked: () -> Unit
) {

    val numberFormat = NumberFormat.getInstance(Locale("en", "IN"))

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 6.dp

    val imageItemJson = Gson().toJson(wishlistItem.productItem)
    val encodedProductItem = URLEncoder.encode(imageItemJson, "UTF-8")

    val product = wishlistItem.productItem

    Column(
        modifier = modifier
            .background(Color.White)
            .width(screenWidth / 2)
            .padding(10.dp)
    ) {

        product.remoteImageUrl?.let { imageUrl ->

            Box {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl)
                            .crossfade(true)
                            .error(R.drawable.retail)
                            .placeholder(R.drawable.custom)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                navController.navigate("${Screen.DisplayScreen.route}/$encodedProductItem")
                            },
                        contentScale = ContentScale.FillBounds,
                        onError = { error ->
                            Log.e(
                                "AsyncImage",
                                "Error loading image: ${error.result.throwable}"
                            )
                        }
                    )

                Box (
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .padding(
                            end = 10.dp,
                            top = 10.dp
                        )
                        .size(25.dp)
                        .clip(shape = CircleShape)
                        .background(Color.White)
                        .border(
                            width = 1.5.dp,
                            shape = CircleShape,
                            color = topbarlightblue
                        )
                        .clickable {
                            onRemoveClicked()
                        }
                ){
                    Image(
                        imageVector = Icons.Default.Close,
                        contentDescription = "remove" ,
                        colorFilter = ColorFilter.tint(topbarlightblue)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .padding(
                            start = 10.dp,
                            bottom = 10.dp
                        )
                        .width(65.dp)
                        .height(25.dp)
                        .clip(
                            RoundedCornerShape(15.dp)
                        )
                        .background(topbarlightblue) ,
                    contentAlignment = Alignment.Center
                ) {
                    Row (
                        modifier = Modifier
                            .padding(horizontal = 7.dp) ,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = product.rating.toString(),
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

        Text(
            modifier = Modifier
                .padding(horizontal = 5.dp , vertical = 2.dp),
            text = product.name,
            color = tintGrey ,
            maxLines = 1 ,
            fontWeight = FontWeight.SemiBold ,
            fontSize = 16.sp ,
            overflow = TextOverflow.Ellipsis
        )

        Row (
            modifier = Modifier ,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.padding(horizontal = 5.dp , vertical = 2.dp),
                text = "₹${numberFormat.format(product.ogprice)}",
                color = tintGrey ,
                textDecoration = TextDecoration.LineThrough ,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(2.dp))

            Row(
                modifier = Modifier ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.rounded_arrow_downward_24),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(1.dp))
                val percentless = percentLess(product.ogprice , product.currprice)
                Text(
                    modifier = Modifier.padding( vertical = 2.dp),
                    text = "${percentless}%",
                    color = rateboxGreen ,
                    fontWeight = FontWeight.Medium
                )
            }

        }

        Text(
            modifier = Modifier.padding(horizontal = 5.dp , vertical = 2.dp),
            text = "₹${numberFormat.format(product.currprice)}",
            color = textcolorblue ,
            fontWeight = FontWeight.Medium ,
            fontSize = 17.sp
        )

        Box (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = 15.dp,
                    bottom = 5.dp
                )
        ){
            BlueButton(
                width_fraction = 0.8f,
                button_text = "Add to Cart" ,
                font_Family = fontBaloo
            ) {

            }
        }
        Spacer(modifier = Modifier.height(10.dp))

    }
}


