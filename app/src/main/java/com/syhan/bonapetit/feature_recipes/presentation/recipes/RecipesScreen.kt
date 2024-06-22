package com.syhan.bonapetit.feature_recipes.presentation.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.syhan.bonapetit.common.data.ErrorType
import com.syhan.bonapetit.common.data.GlobalVariables.fakeShortRecipe
import com.syhan.bonapetit.common.data.NavDestinations
import com.syhan.bonapetit.common.data.NetworkResponse
import com.syhan.bonapetit.common.presentation.ErrorScreen
import com.syhan.bonapetit.common.presentation.LoadingScreen
import com.syhan.bonapetit.common.presentation.theme.BonApetitTheme
import com.syhan.bonapetit.feature_recipes.domain.model.ShortRecipe
import com.syhan.bonapetit.feature_recipes.presentation.recipes.components.RecipeCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipesScreen(
    navHostController: NavHostController,
    viewModel: RecipesViewModel = koinViewModel()
) {
    val response = viewModel.networkResponse.collectAsStateWithLifecycle()
    when (response.value) {
        NetworkResponse.Loading -> {
            LoadingScreen()
        }

        NetworkResponse.UnexpectedError -> {
            ErrorScreen(ErrorType.UnexpectedError)
        }

        NetworkResponse.InternetConnectionError -> {
            ErrorScreen(ErrorType.ConnectionError)
        }

        NetworkResponse.Success -> {
            RecipesContent(
                recipes = viewModel.recipeListState.value.recipeList,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RecipesContent(
    recipes: List<ShortRecipe>,
    navHostController: NavHostController
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 6.dp, end = 6.dp)
    ) {
        items(
            items = recipes,
            key = { recipe: ShortRecipe -> recipe.id }
        ) { recipe ->
            RecipeCard(
                name = recipe.name,
                image = recipe.image,
                cuisine = recipe.cuisine,
                servings = recipe.servings,
                time = (recipe.prepTimeMinutes + recipe.cookTimeMinutes)
            ) {
                navHostController.navigate(NavDestinations.RecipeDetails(recipe.id))
            }
        }
    }
}

@Preview
@Composable
private fun RecipesPreview() {
    BonApetitTheme(
        dynamicColor = false
    ) {
        RecipesContent(
            recipes = listOf(fakeShortRecipe),
            navHostController = rememberNavController()
        )
    }
}