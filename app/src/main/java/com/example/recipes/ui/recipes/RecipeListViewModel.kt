package com.example.recipes.ui.recipes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {

    var uiState by mutableStateOf(RecipeListUiState())
        private set

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            try {
                val recipes = repository.getAllRecipes()
                uiState = uiState.copy(
                    recipes = recipes,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.localizedMessage ?: "Unknown error occurred"
                )
            }
        }
    }
}