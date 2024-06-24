package com.syhan.bonapetit.feature_recipes.presentation.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.data.GlobalVariables.fakeShortRecipe
import com.syhan.bonapetit.common.presentation.ManageInternetConnection
import com.syhan.bonapetit.common.presentation.theme.BonApetitTheme
import com.syhan.bonapetit.feature_recipes.domain.model.ShortRecipe
import com.syhan.bonapetit.feature_recipes.presentation.recipes.components.RecipeCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel = koinViewModel(),
    onNavigate: (Int) -> Unit
) {
    val response = viewModel.networkResponse.collectAsStateWithLifecycle()
 
    ManageInternetConnection(response = response.value, retryAction = { /*TODO*/ }) {
        RecipesContent(
            recipes = viewModel.recipeListState.value.recipeList,
        ) { onNavigate(it) }
    }
}

@Composable
fun RecipesContent(
    recipes: List<ShortRecipe>,
    onNavigate: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.recipes))
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                ) { onNavigate(recipe.id) }
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
        ) { }
    }
}