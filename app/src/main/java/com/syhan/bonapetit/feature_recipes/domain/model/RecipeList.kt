package com.syhan.bonapetit.feature_recipes.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class RecipeList(
    val recipes: List<ShortRecipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)