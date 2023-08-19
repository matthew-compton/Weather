package com.compton.weather

class WeatherRepository {

    private val weatherService = RetrofitInstance.weatherService

    suspend fun getWeather(): WeatherResponse {
        return weatherService.getWeather(
            latitude = "33.75",
            longitude = "84.39"
        )
    }

}