package com.andef.dailyquiz.core.design.card.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.White

@Composable
fun UiCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = colors(),
        content = content
    )
}

private val shape = RoundedCornerShape(size = 40.dp)

@Composable
private fun colors() = CardDefaults.cardColors(containerColor = White, contentColor = Black)