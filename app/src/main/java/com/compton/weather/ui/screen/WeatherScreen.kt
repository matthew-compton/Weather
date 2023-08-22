package com.compton.weather.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.compton.weather.data.local.LocationData
import com.compton.weather.data.local.WeatherData
import com.compton.weather.data.remote.WeatherListResponse
import com.compton.weather.ui.vm.WeatherViewModel
import com.compton.weather.ui.vm.WeatherViewModel.*
import com.compton.weather.util.WeatherUtils

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val weather by viewModel.weatherLiveData.observeAsState(null)
    val location by viewModel.locationLiveData.observeAsState(null)
    val state by viewModel.stateLiveData.observeAsState(State.Loading)

    LaunchedEffect(Unit) {
        if (location == null) {
            viewModel.fetchLocation()
        }
        if (weather == null) {
            viewModel.fetchWeather()
        }
    }

    Column {
        TextInputComponent(viewModel)
        when (state) {
            State.Done -> DoneView(weather)
            State.Loading -> LoadingView()
            State.Error -> ErrorView()
        }
    }

}

@Composable
fun TextInputComponent(
    viewModel: WeatherViewModel
) {
    val city = viewModel.locationLiveData.value?.city ?: ""
    var textValue by remember { mutableStateOf(TextFieldValue(city)) }
    val localFocusManager = LocalFocusManager.current
    Column {
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text("Enter your city") },
            placeholder = { Text(text = "Atlanta") },
            singleLine = true,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    localFocusManager.clearFocus()
                    val location = LocationData(textValue.text)
                    viewModel.updateLocation(location)
                    viewModel.fetchWeather()
                }
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DoneView(
    @PreviewParameter(WeatherPreviewParameterProvider::class) weather: WeatherData?
) {
    Column {
        if (weather == null) {
            EmptyView()
        } else {
            WeatherView(weather)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherView(
    @PreviewParameter(WeatherPreviewParameterProvider::class) weather: WeatherData?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text("City:")
            Text("${weather?.name}")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Temperature:")
            Text("${weather?.temperature}${WeatherUtils.DEGREES_F}")
        }
    }
}

class WeatherPreviewParameterProvider : PreviewParameterProvider<WeatherData> {
    override val values: Sequence<WeatherData>
        get() = sequenceOf(WeatherData.fromWeatherListResponse(WeatherListResponse.mock()))
}

@Preview(showBackground = true)
@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "No results found.")
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "An error has occurred.\nPlease try again.")
    }
}
