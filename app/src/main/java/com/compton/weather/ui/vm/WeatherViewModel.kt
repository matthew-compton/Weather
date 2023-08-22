package com.compton.weather.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compton.weather.data.WeatherRepository
import com.compton.weather.data.local.LocationData
import com.compton.weather.data.local.WeatherData
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    sealed class State {
        object Loading : State()
        object Done : State()
        object Error : State()
    }

    private val stateMutableLiveData = MutableLiveData<State>()
    val stateLiveData: LiveData<State> = stateMutableLiveData

    private val weatherMutableLiveData = MutableLiveData<WeatherData>()
    val weatherLiveData: LiveData<WeatherData> = weatherMutableLiveData

    private val locationMutableLiveData = MutableLiveData<LocationData>()
    val locationLiveData: LiveData<LocationData> = locationMutableLiveData

    fun fetchWeather() {
        stateMutableLiveData.value = State.Loading

        // TODO - check for local cache
        // TODO - check for fresh location data
        // TODO - wait for manual entry

        viewModelScope.launch {
            try {
                val city = locationMutableLiveData.value?.city ?: "Atlanta"
                weatherMutableLiveData.value = repository.getWeather(city)
//                weatherMutableLiveData.value = repository.getWeather(latitude, longitude)
                stateMutableLiveData.value = State.Done
            } catch (e: Exception) {
                Log.e(WeatherViewModel::class.simpleName, "Exception: $e")
                stateMutableLiveData.value = State.Error
            }
        }

    }

    fun updateLocation(location: LocationData) {
        locationMutableLiveData.value = location
        fetchWeather()
    }

}