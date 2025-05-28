package com.example.recipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipes.ui.recipes.RecipeDetailScreen
import com.example.recipes.ui.recipes.RecipeListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "recipeList") {
        composable("recipeList") {
            RecipeListScreen(navController)
        }
        composable("recipeDetail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
            if (recipeId != null) {
                RecipeDetailScreen(recipeId)
            }
        }
    }
}
