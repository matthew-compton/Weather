package com.compton.weather.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compton.weather.data.WeatherRepository
import com.compton.weather.data.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val weatherMutableLiveData = MutableLiveData<WeatherResponse>()
    val weatherLiveData: LiveData<WeatherResponse> = weatherMutableLiveData

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                weatherMutableLiveData.value = repository.getWeather()
            } catch (e: Exception) {
                Log.e(WeatherViewModel::class.simpleName, "Exception: $e")
            }
        }
    }

}