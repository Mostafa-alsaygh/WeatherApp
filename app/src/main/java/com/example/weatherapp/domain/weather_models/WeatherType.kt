package com.example.weatherapp.domain.weather_models

enum class WeatherType(val code: Int, val description: String) {
    ClearSky(0, "Clear sky"),
    MainlyClear(1, "Mainly clear"),
    PartlyCloudy(2, "Partly cloudy"),
    Overcast(3, "Overcast"),
    Fog(45, "Fog"),
    DepositingRimeFog(48, "Depositing rime fog"),
    LightDrizzle(51, "Light drizzle"),
    ModerateDrizzle(53, "Moderate drizzle"),
    IntensityDrizzle(55, "Dense drizzle"),
    LightFreezingDrizzle(56, "Light freezing drizzle"),
    IntensityFreezingDrizzle(57, "Dense freezing drizzle"),
    SlightRain(61, "Slight rain"),
    ModerateRain(63, "Moderate rain"),
    HeavyRain(65, "Heavy rain"),
    LightFreezingRain(66, "Light freezing rain"),
    HeavyFreezingRain(67, "Heavy freezing rain"),
    SlightSnowFall(71, "Slight snow fall"),
    ModerateSnowFall(73, "Moderate snow fall"),
    HeavySnowFall(75, "Heavy snow fall"),
    SnowGrains(77, "Snow grains"),
    SlightRainShowers(80, "Slight rain showers"),
    ModerateRainShowers(81, "Moderate rain showers"),
    ViolentRainShowers(82, "Violent rain showers"),
    SlightSnowShowers(85, "Slight snow showers"),
    HeavySnowShowers(86, "Heavy snow showers"),
    Thunderstorm(95, "Thunderstorm"),
    ThunderstormWithSlightHail(96, "Thunderstorm with slight hail"),
    ThunderstormWithHeavyHail(99, "Thunderstorm with heavy hail"),
    Unknown(-1, "Unknown");

    companion object {
        fun fromCode(code: Int): WeatherType {
            return entries.firstOrNull { it.code == code } ?: Unknown
        }
    }
}
