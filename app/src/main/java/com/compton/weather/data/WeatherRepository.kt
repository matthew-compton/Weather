package com.compton.weather.data

class WeatherRepository(
    private val weatherService: WeatherService
) {

    suspend fun getWeather(): WeatherResponse {
        return weatherService.getWeather(
            latitude = "33.75",
            longitude = "84.39"
        )
    }

}