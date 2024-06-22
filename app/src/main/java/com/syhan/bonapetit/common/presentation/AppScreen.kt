package com.syhan.bonapetit.common.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.syhan.bonapetit.common.data.NavDestinations
import com.syhan.bonapetit.feature_recipes.presentation.RecipeDetailsScreen
import com.syhan.bonapetit.feature_recipes.presentation.RecipesScreen

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    AppContent(navController)
}

@Composable
fun AppContent(navController: NavHostController) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestinations.Recipes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<NavDestinations.Recipes> {
                RecipesScreen()
            }
            composable<NavDestinations.RecipeDetails> {
                RecipeDetailsScreen()
            }
        }
    }
}