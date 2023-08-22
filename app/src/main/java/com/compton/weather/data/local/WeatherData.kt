package com.compton.weather.data.local

import androidx.compose.ui.text.capitalize
import com.compton.weather.data.remote.WeatherListResponse
import java.util.Locale

data class WeatherData(
    val name: String?,
    val latitude: Double?,
    val longitude: Double?,
    val temperature: Double?,
    val iconPath: String?,
    val iconDescription: String?
) {
    companion object {
        fun fromWeatherListResponse(weather: WeatherListResponse): WeatherData {
            return WeatherData(
                name = weather.name,
                latitude = weather.coordinatesResponse.latitude,
                longitude = weather.coordinatesResponse.longitude,
                temperature = weather.locationResponse.temperature,
                iconPath = weather.weatherResponse.first().icon,
                iconDescription = weather.weatherResponse.first().description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            )
        }
    }
}