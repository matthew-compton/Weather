package com.compton.weather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.compton.weather.ui.view.WeatherScreen
import com.compton.weather.ui.vm.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WeatherScreen(weatherViewModel) }
    }

}