package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.weather_models.Weather
import com.example.weatherapp.domain.weather_repositories.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Weather {
        return repository.getWeather(lat, lon)
    }
}
