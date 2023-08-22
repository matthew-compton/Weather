package com.compton.weather.di

import com.compton.weather.ui.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WeatherViewModel(get())
    }
}