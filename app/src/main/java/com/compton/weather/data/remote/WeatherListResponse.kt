package com.compton.weather.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherListResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("main") val locationResponse: LocationResponse,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coordinatesResponse: CoordinatesResponse,
    @SerializedName("weather") val weatherResponse: List<WeatherResponse>,
    @SerializedName("base") val base: String,
    @SerializedName("visibility") val visibility: Long,
    @SerializedName("wind") val windResponse: WindResponse,
    @SerializedName("clouds") val cloudsResponse: CloudsResponse,
    @SerializedName("sys") val sunResponse: SunResponse,
    @SerializedName("timezone") val timezone: Long,
    @SerializedName("cod") val cod: Long,
    @SerializedName("dt") val timestamp: Long
) {
    companion object {
        fun mock(): WeatherListResponse {
            return WeatherListResponse(
                id = 1279556,
                locationResponse = LocationResponse.mock(),
                name = "Yubzha",
                coordinatesResponse = CoordinatesResponse.mock(),
                weatherResponse = WeatherResponse.mockList(),
                base = "stations",
                visibility = 10000,
                windResponse = WindResponse.mock(),
                cloudsResponse = CloudsResponse.mock(),
                sunResponse = SunResponse.mock(),
                timezone = 28800,
                cod = 200,
                timestamp = 1692471934
            )
        }
    }
}

data class CloudsResponse(
    @SerializedName("all") val all: Long
) {
    companion object {
        fun mock(): CloudsResponse {
            return CloudsResponse(
                all = 97
            )
        }
    }
}

data class CoordinatesResponse(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
) {
    companion object {
        fun mock(): CoordinatesResponse {
            return CoordinatesResponse(
                latitude = 33.75,
                longitude = 84.39
            )
        }
    }
}

data class LocationResponse(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("temp_min") val temperatureMinimum: Double,
    @SerializedName("temp_max") val temperatureMaximum: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("pressure") val pressure: Long,
    @SerializedName("humidity") val humidity: Long,
    @SerializedName("sea_level") val seaLevel: Long,
    @SerializedName("grnd_level") val groundLevel: Long
) {
    companion object {
        fun mock(): LocationResponse {
            return LocationResponse(
                temperature = 282.34,
                temperatureMinimum = 282.34,
                temperatureMaximum = 282.34,
                feelsLike = 280.62,
                pressure = 1012,
                humidity = 61,
                seaLevel = 1012,
                groundLevel = 578
            )
        }
    }
}

data class SunResponse(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
) {
    companion object {
        fun mock(): SunResponse {
            return SunResponse(
                country = "CN",
                sunrise = 1692488855,
                sunset = 1692536684
            )
        }
    }
}

data class WeatherResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
) {
    companion object {
        fun mockList(): List<WeatherResponse> {
            return listOf(
                mock(),
                mock(),
                mock()
            )
        }

        private fun mock(): WeatherResponse {
            return WeatherResponse(
                id = 804,
                main = "Clouds",
                description = "overcast clouds",
                icon = "04n"
            )
        }
    }
}

data class WindResponse(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val degree: Long,
    @SerializedName("gust") val gust: Double
) {
    companion object {
        fun mock(): WindResponse {
            return WindResponse(
                speed = 3.12,
                degree = 88,
                gust = 4.32
            )
        }
    }
}