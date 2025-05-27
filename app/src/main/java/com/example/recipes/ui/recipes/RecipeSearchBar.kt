package com.example.recipes.ui.recipes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClear: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text("Search recipes...") },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(Icons.Default.Close, contentDescription = "Clear")
                }
            }
        }
    )
}