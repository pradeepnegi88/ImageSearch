package com.example.imagesearchapp

import android.app.Application
import com.example.imagesearchapp.di.module.appModule
import com.example.imagesearchapp.di.module.dataBaseModule
import com.example.imagesearchapp.di.module.repoModule
import com.example.imagesearchapp.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule,dataBaseModule))
        }
    }
}
