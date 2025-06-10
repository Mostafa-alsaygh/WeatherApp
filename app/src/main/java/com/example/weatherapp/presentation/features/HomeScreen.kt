package com.example.weatherapp.presentation.features

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.components.PressureCard
import com.example.weatherapp.presentation.components.PressureFloatingIconCard
import com.example.weatherapp.presentation.components.TemperatureCard
import com.example.weatherapp.presentation.design.AppTheme
import com.example.weatherapp.presentation.design.Spacer

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF87CEFA),
                        Color.White
                    )
                )
            ),
        state = listState
    ) {
        item {
            val scrollOffset = minOf(listState.firstVisibleItemScrollOffset / 300f, 1f)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        // Example: rotate on X axis
                        rotationX = scrollOffset * 90f

                        // Example: scale down while rotating
                        scaleX = 1f - scrollOffset * 0.5f
                        scaleY = 1f - scrollOffset * 0.5f

                        // Optional: fade out
                        alpha = 1f - scrollOffset
                    },
                horizontalAlignment = CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clear_sky),
                    contentDescription = null,
                )
                Spacer.Medium()

                Text(
                    text = "24°C",
                    style = AppTheme.typography.titleLarge.copy(
                        fontSize = 65.sp,
                        lineHeight = 80.sp,
                        fontWeight = W600
                    ),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF323232)
                )
                Spacer.Small()

                Text(
                    text = "Partly cloudy",
                    style = AppTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.secondary.copy(alpha = 0.6f)
                )
                Spacer.MediumSmall()

                TemperatureCard(32, 45)
            }
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
                    repeat(3) {
                        PressureCard(
                            modifier = Modifier.weight(1f),
                            value = "1012",
                            unit = "hPa",
                            label = "Pressure",
                            iconRes = R.drawable.clear_sky
                        )
                    }
                }
                Spacer.Small()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) {
                        PressureCard(
                            modifier = Modifier.weight(1f),
                            value = "1012",
                            unit = "hPa",
                            label = "Pressure",
                            iconRes = R.drawable.clear_sky
                        )
                    }
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

                Spacer.Small()

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(6) {
                        PressureFloatingIconCard(
                            modifier = Modifier.width(88.dp),
                            value = "1012",
                            unit = "hPa",
                            label = "Pressure",
                            iconRes = painterResource(id = R.drawable.clear_sky)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier)
}