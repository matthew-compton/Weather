package com.compton.weather

import android.app.Application
import com.compton.weather.di.appModule
import com.compton.weather.di.repoModule
import com.compton.weather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

}