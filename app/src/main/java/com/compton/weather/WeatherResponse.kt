package com.compton.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("main") val locationData: LocationData,
    @SerializedName("name") val name: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("visibility") val visibility: Long,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("sys") val sunData: SunData,
    @SerializedName("timezone") val timezone: Long,
    @SerializedName("cod") val cod: Long,
    @SerializedName("dt") val timestamp: Long
)

data class Clouds(
    @SerializedName("all") val all: Long
)

data class Coordinates(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)

data class LocationData(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("temp_min") val temperatureMinimum: Double,
    @SerializedName("temp_max") val temperatureMaximum: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("pressure") val pressure: Long,
    @SerializedName("humidity") val humidity: Long,
    @SerializedName("sea_level") val seaLevel: Long,
    @SerializedName("grnd_level") val groundLevel: Long
)

data class SunData(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class Weather(
    @SerializedName("id") val id: Long,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val degree: Long,
    @SerializedName("gust") val gust: Double
)