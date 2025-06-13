package com.example.weatherapp.presentation.features

import com.example.weatherapp.domain.weather_models.Weather

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val weather: Weather) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
