package com.example.weatherapp.data.model


@kotlinx.serialization.Serializable
data class WeatherApiResponse(
    val current: CurrentWeather,
    val hourly: HourlyWeather,
    val daily: DailyWeather
)

@kotlinx.serialization.Serializable
data class CurrentWeather(
    val temperature_2m: Double,
    val weather_code: Int,
    val wind_speed_10m: Double,
    val relative_humidity_2m: Double,
    val apparent_temperature: Double,
    val pressure_msl: Double,
    val rain: Double,
    val is_day: Int,
    val uv_index: Double
)

@kotlinx.serialization.Serializable
data class HourlyWeather(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val weather_code: List<Int>
)

@kotlinx.serialization.Serializable
data class DailyWeather(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_min: List<Double>,
    val temperature_2m_max: List<Double>
)