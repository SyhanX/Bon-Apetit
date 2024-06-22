package com.syhan.bonapetit.feature_recipes.domain.usecase

import com.syhan.bonapetit.feature_recipes.domain.model.FullRecipe
import com.syhan.bonapetit.feature_recipes.domain.repository.RecipeRepository
import retrofit2.Response

class GetRecipeById(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int) : Response<FullRecipe?> {
        return repository.getRecipeById(id)
    }
}