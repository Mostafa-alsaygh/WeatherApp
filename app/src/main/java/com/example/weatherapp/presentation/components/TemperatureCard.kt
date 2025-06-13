package com.example.weatherapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.design.AppTheme
import com.example.weatherapp.presentation.design.Spacer

@Composable
fun TemperatureCard(
    maxTemp: Int,
    minTemp: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color(0xFFB0CDE5),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Max temperature",
                    tint = Color(0xFF333333),
                    modifier = Modifier.size(16.dp)
                )
                Spacer.Small(vertical = false)
                Text(
                    text = "${maxTemp}°C",
                    color = AppTheme.colors.secondary.copy(alpha = 0.6f),
                    style = AppTheme.typography.titleLarge
                )
            }

            Spacer.Small(vertical = false)

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(1.dp)
                    .background(Color(0x80333333)) // semi-transparent grey
            )

            Spacer.MediumSmall(vertical = false)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Min temperature",
                    tint = Color(0xFF333333),
                    modifier = Modifier.size(16.dp)
                )
                Spacer.Small(vertical = false)
                Text(
                    text = "$minTemp°C",
                    color = AppTheme.colors.secondary.copy(alpha = 0.6f),
                    style = AppTheme.typography.titleLarge
                )
            }
        }
    }
}