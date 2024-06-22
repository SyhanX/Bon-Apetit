package com.syhan.bonapetit.feature_recipes.domain.repository

import com.syhan.bonapetit.feature_recipes.domain.model.FullRecipe
import com.syhan.bonapetit.feature_recipes.domain.model.RecipeList
import retrofit2.Response

interface RecipeRepository {
    suspend fun getRecipeById(id: Int): Response<FullRecipe?>
    suspend fun getAllRecipes(): Response<RecipeList>
}