package com.syhan.bonapetit.feature_recipes.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class ShortRecipe( //for recipes screen
    val id: Int,
    val name: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val difficulty: String,
    val cuisine: String,
    val image: String,
)