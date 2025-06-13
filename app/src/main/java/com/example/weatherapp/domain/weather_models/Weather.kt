package com.example.weatherapp.domain.weather_models

import com.example.weatherapp.domain.emptyDouble
import com.example.weatherapp.domain.emptyString

data class Weather(
    val currentTemperature: Double = emptyDouble(),
    val apparentTemperature: Double = emptyDouble(),
    val weatherType: WeatherType = WeatherType.Unknown,
    val windSpeed: Double = emptyDouble(),
    val humidity: Double = emptyDouble(),
    val pressure: Double = emptyDouble(),
    val rainAmount: Double = emptyDouble(),
    val uvIndex: Double = emptyDouble(),
    val isDay: Int = 0,
    val hourlyForecast: List<HourlyWeather> = emptyList(),
    val dailyForecast: List<DailyWeather> = emptyList(),
    val units: WeatherUnits = WeatherUnits()
)


data class HourlyWeather(
    val time: String = emptyString(),
    val temperature: Double = emptyDouble(),
    val weatherType: WeatherType = WeatherType.Unknown
)

data class DailyWeather(
    val date: String = emptyString(),
    val maxTemperature: Double = emptyDouble() ,
    val minTemperature: Double = emptyDouble(),
    val weatherType: WeatherType = WeatherType.Unknown
)

data class WeatherUnits(
    val temperature: String = emptyString(),
    val windSpeed: String = emptyString(),
    val pressure: String = emptyString(),
    val humidity: String = emptyString(),
    val rain: String = emptyString(),
    val uvIndex: String = emptyString()
)
