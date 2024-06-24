package com.syhan.bonapetit.feature_recipes.presentation.recipes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.bonapetit.common.data.NetworkResponse
import com.syhan.bonapetit.feature_recipes.domain.model.ShortRecipe
import com.syhan.bonapetit.feature_recipes.domain.usecase.RecipeUseCases
import com.syhan.bonapetit.feature_recipes.presentation.recipes.state.RecipesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class RecipesViewModel(
    private val useCases: RecipeUseCases
) : ViewModel() {

    val recipeListState = mutableStateOf(RecipesState())

    private val _networkResponse = MutableStateFlow<NetworkResponse>(NetworkResponse.Loading)
    val networkResponse = _networkResponse.asStateFlow()

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                _networkResponse.value = NetworkResponse.Loading
                useCases.getAllRecipes()
            } catch (e: IOException) {
                _networkResponse.value = NetworkResponse.ConnectionError
                return@launch
            } catch (e: HttpException) {
                _networkResponse.value = NetworkResponse.UnexpectedError
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                _networkResponse.value = NetworkResponse.Success
                recipeListState.value = recipeListState.value.copy(
                    recipeList = response.body()!!.recipes.map {
                        ShortRecipe(
                            id = it.id,
                            name = it.name,
                            prepTimeMinutes = it.prepTimeMinutes,
                            cookTimeMinutes = it.cookTimeMinutes,
                            servings = it.servings,
                            cuisine = it.cuisine,
                            image = it.image,
                        )
                    }
                )
            }
        }
    }
}
