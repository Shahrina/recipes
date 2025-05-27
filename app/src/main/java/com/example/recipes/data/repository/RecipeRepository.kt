package com.example.recipes.data.repository

import com.example.recipes.data.model.Recipe
import com.example.recipes.data.remote.RecipeApiService

class RecipeRepository(private val api: RecipeApiService) {
    private val recipeCache = mutableMapOf<Int, Recipe>()

    suspend fun getAllRecipes(): List<Recipe> {
        return api.getRecipes()
    }

    suspend fun getRecipeById(id: Int): Recipe {
        return recipeCache[id] ?: api.getRecipeById(id).also { recipeCache[id] = it }
    }
}