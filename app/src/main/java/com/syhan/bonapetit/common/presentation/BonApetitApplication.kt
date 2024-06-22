package com.syhan.bonapetit.common.presentation

import android.app.Application
import com.syhan.bonapetit.common.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipeBookApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RecipeBookApplication)
            modules(appModule)
        }
    }
}