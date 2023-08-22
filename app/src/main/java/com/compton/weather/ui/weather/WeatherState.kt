package com.compton.weather.ui.weather

sealed class WeatherState {
    object Empty : WeatherState()
    object Results : WeatherState()
    object Loading : WeatherState()
    object Error : WeatherState()
}