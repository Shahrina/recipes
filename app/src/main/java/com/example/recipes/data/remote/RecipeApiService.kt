package com.example.recipes.data.remote

import com.example.recipes.data.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe
}
