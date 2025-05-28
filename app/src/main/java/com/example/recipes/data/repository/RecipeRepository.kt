package com.example.recipes.data.repository

import com.example.recipes.data.model.Recipe
import com.example.recipes.data.remote.RecipeApiService

class RecipeRepository(private val api: RecipeApiService) {

    private val recipeCache = mutableMapOf<Int, Recipe>()
    private var allRecipesCache: List<Recipe>? = null

    suspend fun getAllRecipes(): List<Recipe> {
        return allRecipesCache ?: api.getRecipes().also { recipes ->
            allRecipesCache = recipes
            recipes.forEach { recipeCache[it.id] = it }
        }
    }

    suspend fun getRecipeById(id: Int): Recipe {
        return recipeCache[id] ?: api.getRecipeById(id).also { recipe ->
            recipeCache[id] = recipe
        }
    }
}