package com.compton.weather.di

import com.compton.weather.data.WeatherRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        WeatherRepository(get())
    }
}