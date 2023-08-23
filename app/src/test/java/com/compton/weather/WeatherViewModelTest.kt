package com.compton.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.compton.weather.data.WeatherRepository
import com.compton.weather.data.local.LocationData
import com.compton.weather.ui.weather.WeatherViewModel
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest : TestCase() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var weatherRepository = Mockito.mock(WeatherRepository::class.java)

    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setup() {
        weatherViewModel = WeatherViewModel(weatherRepository)
    }

    @Test
    fun test_setLocation() {
        // Given
        val location = LocationData("Atlanta")

        // When
        weatherViewModel.setLocation(location)

        // Then
        assertEquals("Atlanta", weatherViewModel.locationLiveData.value?.city)
    }

}