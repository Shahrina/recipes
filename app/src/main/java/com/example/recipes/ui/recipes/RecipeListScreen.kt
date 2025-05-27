package com.example.recipes.ui.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.recipes.data.remote.RemoteDataSource
import com.example.recipes.data.repository.RecipeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen() {
    val viewModel = remember {
        RecipeListViewModel(RecipeRepository(RemoteDataSource.api))
    }
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Recipes") })
                RecipeSearchBar(
                    query = uiState.query,
                    onQueryChanged = { viewModel.searchRecipes(it) },
                    onClear = { viewModel.clearSearch() }
                )
            }
        }
    ) { innerPadding ->
        RecipeListContent(
            recipes = uiState.recipes,
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onRetry = { viewModel.fetchRecipes() },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
