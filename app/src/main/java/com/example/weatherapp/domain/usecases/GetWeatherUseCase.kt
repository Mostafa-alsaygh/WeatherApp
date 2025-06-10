package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.Weather
import com.example.weatherapp.domain.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Weather {
        return repository.getWeather(lat, lon)
    }
}
