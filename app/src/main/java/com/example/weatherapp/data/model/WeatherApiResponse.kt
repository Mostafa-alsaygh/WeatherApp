package com.example.weatherapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherApiResponse(
    val latitude: Double,
    val longitude: Double,
    @SerialName("generationtime_ms") val generationTimeMs: Double,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerialName("current_units") val units: WeatherUnits,
    val current: CurrentWeatherData,
    @SerialName("hourly_units") val hourlyUnits: HourlyUnits,
    val hourly: HourlyWeatherData,
    @SerialName("daily_units") val dailyUnits: DailyUnits,
    val daily: DailyWeatherData
)

@Serializable
data class WeatherUnits(
    val time: String,
    val interval: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("wind_speed_10m") val windSpeed10m: String,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: String,
    @SerialName("apparent_temperature") val apparentTemperature: String,
    @SerialName("pressure_msl") val pressureMsl: String,
    val rain: String,
    @SerialName("is_day") val isDay: String,
    @SerialName("uv_index") val uvIndex: String
)

@Serializable
data class CurrentWeatherData(
    val time: String,
    val interval: Int,
    @SerialName("temperature_2m") val temperature2m: Double,
    @SerialName("weather_code") val weatherCode: Int,
    @SerialName("wind_speed_10m") val windSpeed10m: Double,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: Int,
    @SerialName("apparent_temperature") val apparentTemperature: Double,
    @SerialName("pressure_msl") val pressureMsl: Double,
    val rain: Double,
    @SerialName("is_day") val isDay: Int,
    @SerialName("uv_index") val uvIndex: Double
)

@Serializable
data class HourlyUnits(
    val time: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("weather_code") val weatherCode: String
)

@Serializable
data class HourlyWeatherData(
    val time: List<String>,
    @SerialName("temperature_2m") val temperature2m: List<Double>,
    @SerialName("weather_code") val weatherCode: List<Int>
)

@Serializable
data class DailyUnits(
    val time: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("temperature_2m_min") val temperature2mMin: String,
    @SerialName("temperature_2m_max") val temperature2mMax: String
)

@Serializable
data class DailyWeatherData(
    val time: List<String>,
    @SerialName("weather_code") val weatherCode: List<Int>,
    @SerialName("temperature_2m_min") val temperature2mMin: List<Double>,
    @SerialName("temperature_2m_max") val temperature2mMax: List<Double>
)

