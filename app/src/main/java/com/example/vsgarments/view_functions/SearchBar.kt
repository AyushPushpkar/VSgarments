package com.example.vsgarments.view_functions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.vsgarments.ui.theme.appbackgroundcolor
import com.example.vsgarments.ui.theme.textcolorblue
import com.example.vsgarments.ui.theme.textcolorgrey


@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClearSearch: () -> Unit ,
    modifier: Modifier = Modifier ,
    focusRequester: FocusRequester
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Search products...") },
            modifier = Modifier.weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .focusRequester(focusRequester),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search // Sets tick (✔) button
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch() // Calls search function
                    keyboardController?.hide() // Hides keyboard after search
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = appbackgroundcolor,
                focusedLabelColor =  textcolorblue,
                focusedTextColor = textcolorgrey,
                focusedIndicatorColor = textcolorblue,
                unfocusedLabelColor = textcolorblue,
                unfocusedIndicatorColor = textcolorblue,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = textcolorgrey,
                disabledTextColor = textcolorgrey,
                disabledLabelColor = textcolorblue,
                disabledIndicatorColor = textcolorblue,
                disabledContainerColor = Color.White ,
                cursorColor = textcolorblue,
            )
        )

        if (searchQuery.isNotEmpty()) {
            IconButton(
                onClick = {
                    onClearSearch()
                    keyboardController?.hide() // Hide keyboard when clearing search
                }
            ) {
                Icon(Icons.Default.Close, contentDescription = "Clear search", tint = Color.Gray)
            }
        }

        IconButton(
            onClick = {
                onSearch()
                keyboardController?.hide() // Hide keyboard when clicking search
            }
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
        }
    }
}
