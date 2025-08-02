package com.andef.dailyquiz.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val colorScheme = lightColorScheme(
    primary = IndigoBlue,
    background = IndigoBlue,
    surface = IndigoBlue,
    onPrimary = White,
    onBackground = White,
    onSurface = White
)

@Composable
fun DailyQuizTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}