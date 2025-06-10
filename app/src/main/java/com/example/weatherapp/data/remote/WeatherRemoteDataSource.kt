package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.WeatherApiResponse

interface WeatherRemoteDataSource {
    suspend fun getWeather(lat: Double, lon: Double): WeatherApiResponse
}