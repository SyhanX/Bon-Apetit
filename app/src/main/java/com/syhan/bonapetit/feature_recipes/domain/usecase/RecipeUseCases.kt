package com.syhan.bonapetit.feature_recipes.domain.usecase

data class RecipeUseCases (
    val getAllRecipes: GetAllRecipes,
    val getSingleRecipe: GetRecipeById
)