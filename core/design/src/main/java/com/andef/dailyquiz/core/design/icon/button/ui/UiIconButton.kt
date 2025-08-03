package com.andef.dailyquiz.core.design.icon.button.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.andef.dailyquiz.core.design.White

@Composable
fun UiIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    IconButton(modifier = modifier, colors = colors(), enabled = enabled, onClick = onClick) {
        Icon(painter = icon, contentDescription = contentDescription)
    }
}

@Composable
private fun colors() = IconButtonDefaults.iconButtonColors(
    containerColor = Color.Transparent,
    contentColor = White
)