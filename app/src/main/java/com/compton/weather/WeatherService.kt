package com.compton.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): WeatherResponse

}