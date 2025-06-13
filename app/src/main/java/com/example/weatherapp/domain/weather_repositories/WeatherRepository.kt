package com.example.weatherapp.domain.weather_repositories

import com.example.weatherapp.domain.weather_models.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Weather
}
