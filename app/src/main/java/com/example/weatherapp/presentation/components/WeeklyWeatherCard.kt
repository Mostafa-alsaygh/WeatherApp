package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.weather_models.DailyWeather
import com.example.weatherapp.presentation.design.AppTheme
import com.example.weatherapp.presentation.design.Spacer
import com.example.weatherapp.presentation.getIconRes
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeeklyWeatherCard(
    modifier: Modifier = Modifier,
    forecast: List<DailyWeather>
) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val sortedForecast = remember(forecast) {
        forecast.sortedWith(compareBy {
            val date = LocalDate.parse(it.date, formatter)
            val dayOfWeek = date.dayOfWeek
            if (dayOfWeek == DayOfWeek.SUNDAY) 0 else dayOfWeek.value
        })
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.contentPrimary)
    ) {
        Column {
            sortedForecast.forEachIndexed { index, day ->
                val dayName = LocalDate.parse(day.date, formatter)
                    .dayOfWeek
                    .getDisplayName(TextStyle.FULL, Locale.getDefault()) // e.g., "Sunday"

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = dayName,
                            style = AppTheme.typography.body
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Image(
                                painter = painterResource(day.weatherType.getIconRes()),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                            )

                            Spacer.MediumSmall(vertical = false)

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "↑ ${day.maxTemperature}°C",
                                    style = AppTheme.typography.body,
                                    color = AppTheme.colors.secondary
                                )

                                Spacer.ExtraSmall(vertical = false)

                                Text(
                                    text = "|",
                                    style = AppTheme.typography.headlineMedium,
                                    color = Color(0x3D060414)
                                )

                                Spacer.Small(vertical = false)

                                Text(
                                    text = "↓ ${day.minTemperature}°C",
                                    style = AppTheme.typography.body,
                                    color = AppTheme.colors.secondary
                                )
                            }

                        }
                    }

                    if (index < sortedForecast.lastIndex) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}
