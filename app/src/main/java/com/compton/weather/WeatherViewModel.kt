package com.compton.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val weatherMutableLiveData = MutableLiveData<WeatherResponse>()
    val weatherLiveData: LiveData<WeatherResponse> = weatherMutableLiveData

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val weather = repository.getWeather()
                weatherMutableLiveData.value = weather
            } catch (e: Exception) {
                // Handle error
                Log.e("DEBUG", "Exception: $e")
            }
        }
    }

}