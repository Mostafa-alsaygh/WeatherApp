package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import com.example.weatherapp.domain.DailyWeather
import com.example.weatherapp.domain.HourlyWeather
import com.example.weatherapp.domain.Weather
import com.example.weatherapp.domain.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): Weather {
        val response = remoteDataSource.getWeather(lat, lon)

        // Create hourly forecast
        val hourlyForecast = List(response.hourly.time.size) { index ->
            HourlyWeather(
                time = response.hourly.time[index],
                temperature = response.hourly.temperature_2m[index],
                weatherCode = response.hourly.weather_code[index]
            )
        }

        // Create daily forecast
        val dailyForecast = List(response.daily.time.size) { index ->
            DailyWeather(
                date = response.daily.time[index],
                maxTemperature = response.daily.temperature_2m_max[index],
                minTemperature = response.daily.temperature_2m_min[index],
                weatherCode = response.daily.weather_code[index]
            )
        }

        return Weather(
            currentTemperature = response.current.temperature_2m,
            apparentTemperature = response.current.apparent_temperature,
            weatherCode = response.current.weather_code,
            windSpeed = response.current.wind_speed_10m,
            humidity = response.current.relative_humidity_2m,
            pressure = response.current.pressure_msl,
            rainAmount = response.current.rain,
            uvIndex = response.current.uv_index,
            isDay = response.current.is_day == 1,
            hourlyForecast = hourlyForecast,
            dailyForecast = dailyForecast
        )
    }
}