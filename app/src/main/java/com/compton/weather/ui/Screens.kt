package com.compton.weather.ui

sealed class Screens(val route: String) {
    object Weather : Screens(route = "weather")
}