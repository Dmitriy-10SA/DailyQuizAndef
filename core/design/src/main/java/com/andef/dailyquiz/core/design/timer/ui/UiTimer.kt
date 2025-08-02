package com.andef.dailyquiz.core.design.timer.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.LightGray
import java.util.Locale

@Composable
fun UiTimer(
    modifier: Modifier = Modifier,
    totalTimeSeconds: Int,
    currentTimeSeconds: Int
) {
    val progress = currentTimeSeconds.toFloat() / totalTimeSeconds.coerceAtLeast(1)
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 500)
    )

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentTimeSeconds),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                letterSpacing = 0.sp,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,
                color = textColor
            )
            Text(
                text = formatTime(totalTimeSeconds),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                letterSpacing = 0.sp,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(containerColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .clip(RoundedCornerShape(6.dp))
                    .background(textColor)
            )
        }
    }
}

private val containerColor = LightGray

private val textColor = DeepPurple

private fun formatTime(seconds: Int): String {
    val minutesPart = seconds / 60
    val secondsPart = seconds % 60
    return String.format(Locale.US, "%02d:%02d", minutesPart, secondsPart)
}