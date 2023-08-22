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

        // Check for location data
        if (locationMutableLiveData.value == null) {
            stateMutableLiveData.value = State.Done
            return
        }

        viewModelScope.launch {
            try {

                // Check for city data
                val city = locationMutableLiveData.value!!.city
                if (!city.isNullOrEmpty()) {
                    weatherMutableLiveData.value = repository.getWeather(city)
                    stateMutableLiveData.value = State.Done
                    return@launch
                }

                // Check for coordinate data
                val latitude = locationMutableLiveData.value!!.latitude
                val longitude = locationMutableLiveData.value!!.longitude
                if (latitude != null && longitude != null) {
                    weatherMutableLiveData.value =
                        repository.getWeather(latitude.toString(), longitude.toString())
                    stateMutableLiveData.value = State.Done
                    return@launch
                }

            } catch (e: Exception) {
                Log.e(WeatherViewModel::class.simpleName, "Exception: $e")
                stateMutableLiveData.value = State.Error
            }
        }
    }

    fun fetchLocation() {
        // TODO
    }

    fun updateLocation(location: LocationData) {
        locationMutableLiveData.value = location
    }

    fun onLocationCheckFailed() {
        stateMutableLiveData.value = State.Done
    }

    fun onLocationCheckCanceled() {
        stateMutableLiveData.value = State.Done
    }

}