package com.example.weatherapp.presentation.features

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather_models.Weather
import com.example.weatherapp.presentation.components.PressureCard
import com.example.weatherapp.presentation.components.PressureFloatingIconCard
import com.example.weatherapp.presentation.components.WeatherHeader
import com.example.weatherapp.presentation.components.WeeklyWeatherCard
import com.example.weatherapp.presentation.design.AppTheme
import com.example.weatherapp.presentation.design.Spacer
import com.example.weatherapp.presentation.getIconRes
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = koinViewModel<WeatherViewModel>()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    var hasRequestedWeather by remember { mutableStateOf(false) }

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val permissionState = remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            permissionState.value = granted
        }
    )

    LaunchedEffect(Unit) {
        val permissionCheck = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            permissionState.value = true
        } else {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    LaunchedEffect(permissionState.value) {
        if (permissionState.value && !hasRequestedWeather) {
            try {
                val locationRequest = LocationRequest
                    .Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                    .setMaxUpdates(1)
                    .build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        val location = result.lastLocation
                        if (location != null) {
                            viewModel.loadWeather(lat = location.latitude, lon = location.longitude)
                        } else {
                            viewModel.loadWeather(lat = 50.0, lon = 31.0)
                        }
                        hasRequestedWeather = true
                        fusedLocationClient.removeLocationUpdates(this)
                    }
                }

                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )

            } catch (e: SecurityException) {
                viewModel.loadWeather(lat = 60.0, lon = 31.0)
                hasRequestedWeather = true
            }
        }
    }

    when (uiState) {
        is WeatherUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is WeatherUiState.Success -> {
            val weather = (uiState as WeatherUiState.Success).weather
            WeatherContent(
                weather = weather,
                modifier = modifier
            )
        }

        is WeatherUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(uiState as WeatherUiState.Error).message}")
            }
        }
    }
}


@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun WeatherContent(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val scrollOffset by remember {
        derivedStateOf {
            val firstVisibleItem = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            if (firstVisibleItem == 1) offset else if (firstVisibleItem > 1) 300 else 0
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(AppTheme.colors.primary, Color.White)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        item {
            Row(
                modifier = Modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Mosul",
                    style = AppTheme.typography.titleLarge,
                    color = Color(0xFF323232),
                    textAlign = TextAlign.End
                )
            }
        }

        item {
            WeatherHeader(
                weather = weather,
                scrollOffset = scrollOffset,
                modifier = Modifier.wrapContentHeight()
            )
        }


        item {
            Column(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 24.dp, start = 12.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PressureCard(
                        modifier = Modifier.weight(1f),
                        value = weather.windSpeed.toInt().toString(),
                        unit = weather.units.windSpeed,
                        label = "Wind",
                        iconRes = R.drawable.fast_wind
                    )
                    PressureCard(
                        modifier = Modifier.weight(1f),
                        value = weather.humidity.toInt().toString(),
                        unit = weather.units.humidity,
                        label = "Humidity",
                        iconRes = R.drawable.humidity
                    )
                    PressureCard(
                        modifier = Modifier.weight(1f),
                        value = weather.rainAmount.toString(),
                        unit = weather.units.rain,
                        label = "Rain",
                        iconRes = R.drawable.rain
                    )
                }
            }
            Column(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 8.dp, start = 12.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PressureCard(
                        modifier = Modifier.weight(1f),
                        value = weather.uvIndex.toString(),
                        unit = weather.units.windSpeed,
                        label = "UV Index",
                        iconRes = R.drawable.uv_02
                    )
                    PressureCard(
                        modifier = Modifier.weight(1f),
                        value = weather.pressure.toString(),
                        unit = weather.units.pressure,
                        label = "Pressure",
                        iconRes = R.drawable.arrow_down_05
                    )
                    PressureCard(
                        modifier = Modifier.weight(1f),
                        value = weather.apparentTemperature.toString(),
                        unit = weather.units.rain,
                        label = "Feels like",
                        iconRes = R.drawable.temperature
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "Today",
                    style = AppTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.secondary
                )

                Spacer.Medium()
                Spacer.Medium()

                LazyRow(

                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(weather.hourlyForecast.take(24)) { hour ->
                        PressureFloatingIconCard(
                            modifier = Modifier.width(88.dp),
                            value = hour.temperature.toInt().toString(),
                            unit = "°C",
                            label = hour.weatherType.description,
                            iconRes = painterResource(id = weather.weatherType.getIconRes())
                        )
                    }
                }

                Spacer.Medium()

                Text(
                    text = "Next 7 days",
                    style = AppTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.secondary
                )
                Spacer.Medium()

                WeeklyWeatherCard(
                    modifier = Modifier.fillMaxWidth(),
                    forecast = weather.dailyForecast
                )
            }
        }
    }
}
