package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.WeatherApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient
) : WeatherRemoteDataSource {

    override suspend fun getWeather(lat: Double, lon: Double): WeatherApiResponse {
        return client.get("https://api.open-meteo.com/v1/forecast") {
            parameter("latitude", lat)
            parameter("longitude", lon)
            parameter("daily", "weather_code,temperature_2m_min,temperature_2m_max")
            parameter("hourly", "temperature_2m,weather_code")
            parameter("current", "temperature_2m,weather_code,wind_speed_10m,relative_humidity_2m,apparent_temperature,pressure_msl,rain,is_day,uv_index")
            parameter("timezone", "auto")
        }.body()
    }
}
