package com.syhan.bonapetit.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syhan.bonapetit.common.data.NavDestinations
import com.syhan.bonapetit.feature_recipes.presentation.details.RecipeDetailsScreen
import com.syhan.bonapetit.feature_recipes.presentation.recipes.RecipesScreen

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
    AppContent(navController)
}

@Composable
fun AppContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.Recipes,
    ) {
        composable<NavDestinations.Recipes> {
            RecipesScreen { navController.navigate(NavDestinations.RecipeDetails(it)) }
        }
        composable<NavDestinations.RecipeDetails> {
            RecipeDetailsScreen { navController.navigateUp() }
        }
    }
}