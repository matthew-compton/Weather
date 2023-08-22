package com.compton.weather

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.compton.weather.data.local.LocationData
import com.compton.weather.ui.theme.WeatherTheme
import com.compton.weather.ui.weather.WeatherViewModel
import com.compton.weather.util.getCachedLocation
import com.compton.weather.util.isLocationPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupLocation()
    }

    private fun setupUI() {
        setContent {
            WeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController, weatherViewModel = weatherViewModel)
                }
            }
        }
    }

    private fun setupLocation() {
        val city = getCachedLocation(this)
        if (city.isNotEmpty()) {
            val location = LocationData(city)
            weatherViewModel.setLocation(location)
            weatherViewModel.fetchWeather()
        } else {
            setupLocationProvider()
        }
    }

    @SuppressLint("MissingPermission")
    private fun setupLocationProvider() {
        if (isLocationPermissionGranted(this)) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    Log.i(MainActivity::class.simpleName, "Location Data: $location")
                    val latitude = location?.latitude ?: 0.0
                    val longitude = location?.longitude ?: 0.0
                    Log.i(MainActivity::class.simpleName, "Coordinate Data: $latitude, $longitude")
                    val gcd = Geocoder(this, Locale.getDefault())
                    val maxResults = 1
                    val addresses: List<Address>? = gcd.getFromLocation(
                        latitude,
                        longitude,
                        maxResults
                    )
                    var city = ""
                    if (addresses!!.isNotEmpty()) {
                        city = addresses.first().locality
                        Log.i(MainActivity::class.simpleName, "City Data: $city")
                    }
                    val locationData = LocationData(city, latitude, longitude)
                    weatherViewModel.setLocation(locationData)
                    weatherViewModel.fetchWeather()
                }
                .addOnFailureListener {
                    Log.i(MainActivity::class.simpleName, "LocationServices failed.")
                    weatherViewModel.onLocationCheckFailed()
                }
                .addOnCanceledListener {
                    Log.i(MainActivity::class.simpleName, "LocationServices canceled.")
                    weatherViewModel.onLocationCheckCanceled()
                }
        } else {
            Log.i(MainActivity::class.simpleName, "Location Permission not granted.")
            weatherViewModel.onLocationPermissionDenied()
        }
    }

}