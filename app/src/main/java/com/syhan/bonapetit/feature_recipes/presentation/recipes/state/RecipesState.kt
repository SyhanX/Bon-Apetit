package com.syhan.bonapetit.feature_recipes.presentation.recipes.state

import com.syhan.bonapetit.feature_recipes.domain.model.ShortRecipe

data class RecipesState(
    val recipeList: List<ShortRecipe> = emptyList()
)