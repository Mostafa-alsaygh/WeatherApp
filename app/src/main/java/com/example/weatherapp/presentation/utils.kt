package com.example.weatherapp.presentation

import com.example.weatherapp.R
import com.example.weatherapp.domain.weather_models.WeatherType

fun WeatherType.getIconRes(): Int {
    return when (this) {
        WeatherType.ClearSky -> R.drawable.clear_sky
        WeatherType.MainlyClear -> R.drawable.mainly_clear
        WeatherType.PartlyCloudy -> R.drawable.partly_cloudy
        WeatherType.Overcast -> R.drawable.overcast
        WeatherType.Fog, WeatherType.DepositingRimeFog -> R.drawable.fog
        WeatherType.LightDrizzle ->R.drawable.drizzle_light
        WeatherType.ModerateDrizzle -> R.drawable.drizzle_moderate
        WeatherType.IntensityDrizzle -> R.drawable.drizzle_intensity
        WeatherType.LightFreezingDrizzle ->R.drawable.freezing_drizzle_light
        WeatherType.IntensityFreezingDrizzle -> R.drawable.freezing_drizzle_intensity
        WeatherType.SlightRain-> R.drawable.rain_slight
        WeatherType.ModerateRain->R.drawable.rain_moderate
        WeatherType.HeavyRain -> R.drawable.rain_intensity
        WeatherType.LightFreezingRain ->R.drawable.freezing_loght
        WeatherType.HeavyFreezingRain -> R.drawable.freezing_heavy
        WeatherType.SlightSnowFall -> R.drawable.snow_fall_light
        WeatherType.ModerateSnowFall -> R.drawable.snow_fall_moderate
        WeatherType.HeavySnowFall -> R.drawable.snow_fall_intensity
        WeatherType.SnowGrains -> R.drawable.freezing_drizzle_intensity
        WeatherType.SlightRainShowers -> R.drawable.rain_shower_slight
        WeatherType.ModerateRainShowers -> R.drawable.rain_shower_moderate
        WeatherType.ViolentRainShowers -> R.drawable.rain_shower_violent
        WeatherType.SlightSnowShowers -> R.drawable.freezing_drizzle_intensity
        WeatherType.HeavySnowShowers -> R.drawable.snow_shower_heavy
        WeatherType.Thunderstorm -> R.drawable.thunderstrom_slight_or_moderate
        WeatherType.ThunderstormWithSlightHail -> R.drawable.thunderstrom_with_slight_hail
        WeatherType.ThunderstormWithHeavyHail -> R.drawable.thunderstrom_with_heavy_hail
        WeatherType.Unknown -> R.drawable.clear_sky
    }
}
