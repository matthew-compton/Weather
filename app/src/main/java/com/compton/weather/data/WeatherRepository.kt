package com.compton.weather.data

import com.compton.weather.data.local.WeatherData
import com.compton.weather.data.remote.WeatherService

class WeatherRepository(
    private val weatherService: WeatherService
) {

    suspend fun getWeather(latitude: String, longitude: String): WeatherData {
        return WeatherData.fromWeatherListResponse(
            weatherService.getWeather(
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    suspend fun getWeather(city: String): WeatherData {
        return WeatherData.fromWeatherListResponse(
            weatherService.getWeather(
                city = city
            )
        )
    }

}