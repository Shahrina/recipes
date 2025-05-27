package com.example.recipes.ui.recipes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.model.Recipe
import com.example.recipes.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {

    var uiState by mutableStateOf(RecipeListUiState())
        private set
    private var allRecipes: List<Recipe> = emptyList()

    init {
        fetchRecipes()
    }


    fun fetchRecipes() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                val recipes = repository.getAllRecipes()
                allRecipes = recipes
                uiState = uiState.copy(recipes = recipes, isLoading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.localizedMessage ?: "Error loading recipes"
                )
            }
        }
    }

    fun searchRecipes(query: String) {
        val filtered = if (query.isBlank()) {
            allRecipes
        } else {
            allRecipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        (it.cuisine?.contains(query, ignoreCase = true) == true)
            }
        }

        uiState = uiState.copy(query = query, recipes = filtered)
    }

    fun clearSearch() {
        uiState = uiState.copy(query = "", recipes = allRecipes)
    }
}