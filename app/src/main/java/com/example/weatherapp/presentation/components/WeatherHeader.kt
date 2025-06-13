package com.example.weatherapp.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather_models.Weather
import com.example.weatherapp.presentation.design.AppTheme
import com.example.weatherapp.presentation.getIconRes
import kotlin.math.roundToInt

@Composable
fun WeatherHeader(
    weather: Weather,
    scrollOffset: Int,
    modifier: Modifier = Modifier
) {
    val progress = minOf(scrollOffset / 300f, 1f).coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(((1f - progress) * 0).toInt(), 0) }
                .graphicsLayer {
                    alpha = 1f - progress
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(animateDpAsState(if (progress > 0.5f) 120.dp else 200.dp).value)
            ) {
                Image(
                    painter = painterResource(id = weather.weatherType.getIconRes()),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${weather.currentTemperature.roundToInt()}°C",
                style = AppTheme.typography.titleLarge.copy(
                    fontSize = animateFloatAsState(
                        if (progress > 0.5f) 32.sp.value else 65.sp.value
                    ).value.sp
                ),
                color = AppTheme.colors.secondary,
                textAlign = TextAlign.Center
            )

            com.example.weatherapp.presentation.design.Spacer.Small()

            Text(
                text = weather.weatherType.description,
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colors.secondary.copy(
                    alpha = animateFloatAsState(0.6f - (progress * 0.4f)).value
                ),
                textAlign = TextAlign.Center
            )

            com.example.weatherapp.presentation.design.Spacer.MediumSmall()

            TemperatureCard(
                maxTemp = (weather.dailyForecast.firstOrNull()?.maxTemperature ?: 0.0).toInt(),
                minTemp = (weather.dailyForecast.firstOrNull()?.minTemperature ?: 0.0).toInt(),
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = 1f - (progress * 0.5f)
                        scaleY = 1f - (progress * 0.5f)
                    }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset((progress * 100).toInt(), 0) }
                .graphicsLayer {
                    alpha = progress
                }
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = weather.weatherType.getIconRes()),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .graphicsLayer {
                        scaleX = 0.8f + (progress * 0.2f)
                        scaleY = 0.8f + (progress * 0.2f)
                    }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${weather.currentTemperature.roundToInt()}°C",
                    style = AppTheme.typography.titleLarge.copy(
                        fontSize = animateFloatAsState(32.sp.value + (progress * 10f)).value.sp
                    ),
                    color = Color(0xFF323232),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = weather.weatherType.description,
                    style = AppTheme.typography.titleMedium,
                    color = AppTheme.colors.secondary.copy(
                        alpha = animateFloatAsState(0.2f + (progress * 0.4f)).value
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                TemperatureCard(
                    maxTemp = (weather.dailyForecast.firstOrNull()?.maxTemperature ?: 0.0).toInt(),
                    minTemp = (weather.dailyForecast.firstOrNull()?.minTemperature ?: 0.0).toInt(),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .graphicsLayer {
                            scaleX = 0.5f + (progress * 0.5f)
                            scaleY = 0.5f + (progress * 0.5f)
                        }
                )
            }
        }
    }
}

