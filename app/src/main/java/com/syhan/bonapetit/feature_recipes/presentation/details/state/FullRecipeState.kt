package com.syhan.bonapetit.feature_recipes.presentation.details.state

data class FullRecipeState(
    val id: Int = 0,
    val name: String = "",
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val prepTimeMinutes: Int = 0,
    val cookTimeMinutes: Int = 0,
    val servings: Int = 0,
    val difficulty: String = "",
    val cuisine: String = "",
    val caloriesPerServing: Int = 0,
    val tags: List<String> = emptyList(),
    val userId: Int = 0,
    val image: String = "",
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val mealType: List<String> = emptyList()
)