package com.example.weatherapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.design.AppTheme

@Composable
fun PressureCard(
    modifier: Modifier = Modifier,
    value: String,
    unit: String,
    label: String,
    @DrawableRes iconRes: Int
) {
    Box(
        modifier = modifier
            .height(140.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(20.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color(0xFF64B5F6), // light blue color
                modifier = Modifier
                    .size(32.dp)
                    .padding(bottom = 4.dp)
            )

            Text(
                text = "$value $unit",
                style = AppTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = Color(0xFF212121)
            )

            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                )
            )
        }
    }
}