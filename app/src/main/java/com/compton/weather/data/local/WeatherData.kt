package com.compton.weather.data.local

import com.compton.weather.data.remote.WeatherListResponse

data class WeatherData(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val temperature: Double
) {
    companion object {
        fun fromWeatherListResponse(weather: WeatherListResponse): WeatherData {
            return WeatherData(
                name = weather.name,
                latitude = weather.coordinatesResponse.latitude,
                longitude = weather.coordinatesResponse.longitude,
                temperature = weather.locationResponse.temperature
            )
        }
    }
}