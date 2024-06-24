package com.syhan.bonapetit.feature_recipes.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.bonapetit.common.data.NetworkResponse
import com.syhan.bonapetit.feature_recipes.domain.usecase.RecipeUseCases
import com.syhan.bonapetit.feature_recipes.presentation.details.state.FullRecipeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class RecipeDetailsViewModel(
    private val useCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val recipeState = mutableStateOf(FullRecipeState())

    private val _networkResponse = MutableStateFlow<NetworkResponse>(NetworkResponse.Loading)
    val networkResponse = _networkResponse.asStateFlow()

    private val arg = savedStateHandle.get<Int>("recipeId")

    init {
        arg?.let {
            getSingleRecipe(id = it)
        }
    }

    private fun getSingleRecipe(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                _networkResponse.value = NetworkResponse.Loading
                useCases.getSingleRecipe(id)
            } catch (e: HttpException) {
                _networkResponse.value = NetworkResponse.ConnectionError
                return@launch
            } catch (e: IOException) {
                _networkResponse.value = NetworkResponse.UnexpectedError
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                _networkResponse.value = NetworkResponse.Success
                response.body()?.let {
                    recipeState.value = recipeState.value.copy(
                        id = it.id,
                        name = it.name,
                        ingredients = it.ingredients,
                        instructions = it.instructions,
                        prepTimeMinutes = it.prepTimeMinutes,
                        cookTimeMinutes = it.cookTimeMinutes,
                        servings = it.servings,
                        difficulty = it.difficulty,
                        cuisine = it.cuisine,
                        caloriesPerServing = it.caloriesPerServing,
                        tags = it.tags,
                        userId = it.userId,
                        image = it.image,
                        rating = it.rating,
                        reviewCount = it.reviewCount,
                        mealType = it.mealType
                    )
                }
            }
        }
    }
}