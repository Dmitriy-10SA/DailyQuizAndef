package com.andef.dailyquiz.core.design.loading.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.andef.dailyquiz.core.design.SoftLilac
import com.andef.dailyquiz.core.design.White

@Composable
fun UiLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = SoftLilac,
            trackColor = White,
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Round
        )
    }
}