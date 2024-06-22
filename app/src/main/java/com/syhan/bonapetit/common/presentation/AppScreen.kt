package com.syhan.bonapetit.common.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.data.GlobalVariables.ROUTE_RECIPES
import com.syhan.bonapetit.common.data.NavDestinations
import com.syhan.bonapetit.feature_recipes.presentation.details.RecipeDetailsScreen
import com.syhan.bonapetit.feature_recipes.presentation.recipes.RecipesScreen

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isCurrentRouteDefault = currentRoute == ROUTE_RECIPES
    AppContent(navController, isCurrentRouteDefault)
}

@Composable
fun AppContent(navController: NavHostController, isCurrentRouteDefault: Boolean) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    if (!isCurrentRouteDefault) {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestinations.Recipes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<NavDestinations.Recipes> {
                RecipesScreen(navController)
            }
            composable<NavDestinations.RecipeDetails> {
                RecipeDetailsScreen()
            }
        }
    }
}