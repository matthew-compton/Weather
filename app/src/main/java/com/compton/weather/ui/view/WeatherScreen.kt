package com.compton.weather.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.compton.weather.util.WeatherUtils
import com.compton.weather.ui.vm.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val weather by viewModel.weatherLiveData.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    Column {
        if (weather == null) {
            CircularProgressIndicator()
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Current Temperature:")
                    Text("${weather!!.locationData.temperature}${WeatherUtils.DEGREES_F}")
                }
            }
        }
    }

}