package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.weather_models.WeatherType
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import com.example.weatherapp.domain.weather_models.DailyWeather
import com.example.weatherapp.domain.weather_models.HourlyWeather
import com.example.weatherapp.domain.weather_models.Weather
import com.example.weatherapp.domain.weather_models.WeatherUnits
import com.example.weatherapp.domain.weather_repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): Weather {
        val response = remoteDataSource.getWeather(lat, lon)

        val hourlyForecast = response.hourly.time.mapIndexed { index, time ->
            HourlyWeather(
                time = time,
                temperature = response.hourly.temperature2m[index],
                weatherType = WeatherType.fromCode(response.hourly.weatherCode[index])
            )
        }

        val dailyForecast = response.daily.time.mapIndexed { index, date ->
            DailyWeather(
                date = date,
                maxTemperature = response.daily.temperature2mMax[index],
                minTemperature = response.daily.temperature2mMin[index],
                weatherType = WeatherType.fromCode(response.daily.weatherCode[index])
            )
        }

        val units = WeatherUnits(
            temperature = response.units.temperature2m,
            windSpeed = response.units.windSpeed10m,
            pressure = response.units.pressureMsl,
            humidity = response.units.relativeHumidity2m,
            rain = response.units.rain,
            uvIndex = response.units.uvIndex
        )


        return Weather(
            currentTemperature = response.current.temperature2m,
            apparentTemperature = response.current.apparentTemperature,
            weatherType = WeatherType.fromCode(response.current.weatherCode),
            windSpeed = response.current.windSpeed10m,
            humidity = response.current.relativeHumidity2m.toDouble(),
            pressure = response.current.pressureMsl,
            rainAmount = response.current.rain,
            uvIndex = response.current.uvIndex,
            isDay = response.current.isDay,
            hourlyForecast = hourlyForecast,
            dailyForecast = dailyForecast,
            units = units
        )
    }
}