package com.syhan.bonapetit.feature_recipes.data.remote

import com.syhan.bonapetit.feature_recipes.domain.model.FullRecipe
import com.syhan.bonapetit.feature_recipes.domain.model.RecipeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {
    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Response<FullRecipe?>

    @GET("recipes?select=name,image,servings,cuisine,prepTimeMinutes,cookTimeMinutes")
    suspend fun getAllRecipes() : Response<RecipeList>
}