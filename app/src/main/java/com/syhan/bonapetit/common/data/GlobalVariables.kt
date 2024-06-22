package com.syhan.bonapetit.common.data

import com.syhan.bonapetit.feature_recipes.domain.model.ShortRecipe

object GlobalVariables {
    val fakeShortRecipe = ShortRecipe(
        name = "Veranstaltungsinformationsdienst",
        cuisine = "Indonesian",
        servings = 7,
        id = 0,
        cookTimeMinutes = 1000,
        prepTimeMinutes = 337,
        image = ":)"
    )

    const val ROUTE_RECIPES = "com.syhan.bonapetit.common.data.NavDestinations.Recipes"
    const val ROUTE_DETAILS =
        "com.syhan.bonapetit.common.data.NavDestinations.RecipeDetails/{recipeId}"
}