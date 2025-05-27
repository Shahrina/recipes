package com.example.recipes.ui.theme.recipes

import com.example.recipes.data.model.Recipe

data class RecipeListUiState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
