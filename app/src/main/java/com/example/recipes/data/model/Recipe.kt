package com.example.recipes.data.model

import com.squareup.moshi.Json

data class Recipe(
    val id: Int,
    val title: String,
    @Json(name = "photoUrl") val image: String?,
    val course: String?,
    val cuisine: String?,
    val ingredients: String?,
    val directions: String?,
    val servings: Int?,
    val prepTime: String?,
    val cookTime: String?
)