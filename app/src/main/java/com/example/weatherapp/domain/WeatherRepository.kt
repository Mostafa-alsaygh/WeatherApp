package com.example.weatherapp.domain

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Weather
}
