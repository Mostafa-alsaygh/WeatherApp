package com.example.weatherapp.domain

data class Weather(
    val currentTemperature: Double,
    val apparentTemperature: Double,
    val weatherCode: Int,
    val windSpeed: Double,
    val humidity: Double,
    val pressure: Double,
    val rainAmount: Double,
    val uvIndex: Double,
    val isDay: Boolean,
    val hourlyForecast: List<HourlyWeather>,
    val dailyForecast: List<DailyWeather>
)

data class HourlyWeather(
    val time: String,
    val temperature: Double,
    val weatherCode: Int
)

data class DailyWeather(
    val date: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val weatherCode: Int
)