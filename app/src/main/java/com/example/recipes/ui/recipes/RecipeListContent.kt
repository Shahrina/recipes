package com.example.recipes.ui.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipes.data.model.Recipe

@Composable
fun RecipeListContent(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    query: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            isLoading -> LoadingIndicator()
            errorMessage != null -> ErrorMessage(message = errorMessage, onRetry = onRetry)
            recipes.isEmpty() -> EmptyStateMessage(query)
            else -> LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(recipes) { recipe ->
                    RecipeListItem(recipe = recipe, onClick = { onRecipeClick(recipe) })
                }
            }
        }
    }
}
