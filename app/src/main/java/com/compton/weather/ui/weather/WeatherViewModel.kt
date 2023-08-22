package com.compton.weather.ui.weather

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

    private val stateMutableLiveData = MutableLiveData<WeatherState>()
    val stateLiveData: LiveData<WeatherState> = stateMutableLiveData

    private val weatherMutableLiveData = MutableLiveData<WeatherData>()
    val weatherLiveData: LiveData<WeatherData> = weatherMutableLiveData

    private val locationMutableLiveData = MutableLiveData<LocationData>()
    val locationLiveData: LiveData<LocationData> = locationMutableLiveData

    fun fetchWeather() {
        stateMutableLiveData.value = WeatherState.Loading

        // Check for location data
        if (locationMutableLiveData.value == null) {
            stateMutableLiveData.value = WeatherState.Empty
            return
        }

        viewModelScope.launch {
            try {

                // Check for city data
                val city = locationMutableLiveData.value!!.city
                if (!city.isNullOrEmpty()) {
                    val weather = repository.getWeather(city)
                    weatherMutableLiveData.value = weather
                    stateMutableLiveData.value = WeatherState.Results
                    return@launch
                }

                // Check for coordinate data
                val latitude = locationMutableLiveData.value!!.latitude
                val longitude = locationMutableLiveData.value!!.longitude
                if (latitude != null && longitude != null) {
                    val weather = repository.getWeather(latitude.toString(), longitude.toString())
                    weatherMutableLiveData.value = weather
                    stateMutableLiveData.value = WeatherState.Results
                    return@launch
                }

            } catch (e: Exception) {
                Log.e(WeatherViewModel::class.simpleName, "Exception: $e")
                stateMutableLiveData.value = WeatherState.Error
            }
        }
    }

    fun setLocation(location: LocationData) {
        locationMutableLiveData.value = location
    }

    fun onLocationCheckFailed() {
        stateMutableLiveData.value = WeatherState.Empty
    }

    fun onLocationCheckCanceled() {
        stateMutableLiveData.value = WeatherState.Empty
    }

}