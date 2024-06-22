package com.syhan.bonapetit.common.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.syhan.bonapetit.feature_recipes.data.remote.RecipeApi
import com.syhan.bonapetit.feature_recipes.data.repository.RecipeRepositoryImpl
import com.syhan.bonapetit.feature_recipes.domain.repository.RecipeRepository
import com.syhan.bonapetit.feature_recipes.domain.usecase.GetAllRecipes
import com.syhan.bonapetit.feature_recipes.domain.usecase.GetRecipeById
import com.syhan.bonapetit.feature_recipes.domain.usecase.RecipeUseCases
import com.syhan.bonapetit.feature_recipes.presentation.recipes.RecipesViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RecipeApi::class.java)
    }
    single<RecipeRepository> {
        RecipeRepositoryImpl(get())
    }
    single {
        RecipeUseCases(
            getAllRecipes = GetAllRecipes(get()),
            getSingleRecipe = GetRecipeById(get())
        )
    }
    viewModel {
        RecipesViewModel(get())
    }
//    viewModel {
//            (handle: SavedStateHandle) -> DetailsViewModel(get(), handle)
//    }
}