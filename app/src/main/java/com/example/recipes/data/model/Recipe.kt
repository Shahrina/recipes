package com.example.recipes.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val course: String,
    val cuisine: String,
    val ingredients: List<String> = emptyList(),
    val instructions: String?,
    val servings: Int?,
    val prepTime: String?,
    val cookTime: String?
)