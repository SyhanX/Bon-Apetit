package com.syhan.bonapetit.common.data

import kotlinx.serialization.Serializable

sealed interface NavDestinations {
    @Serializable
    data object Recipes : NavDestinations
    @Serializable
    data class RecipeDetails(val recipeId: Int) : NavDestinations
}