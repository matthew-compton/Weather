package com.compton.weather

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compton.weather.ui.Screens
import com.compton.weather.ui.view.WeatherScreen
import com.compton.weather.ui.vm.WeatherViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Weather.route
    )
    {
        composable(route = Screens.Weather.route) {
            WeatherScreen(viewModel = weatherViewModel)
        }
    }
}