package com.compton.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val weather by viewModel.weatherLiveData.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    Column {
        if (weather == null) {
            Text(text = "Loading...")
        } else {
            Text(text = "ID: ${weather!!.id}")
        }
    }

}