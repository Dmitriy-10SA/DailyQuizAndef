package com.andef.dailyquiz.core.design.button.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.White

sealed class UiButtonType(val backgroundColor: Color, val contentColor: Color) {
    data object Main : UiButtonType(backgroundColor = IndigoBlue, contentColor = White)
    data object Secondary : UiButtonType(backgroundColor = White, contentColor = DeepPurple)
    data class WithIcon(
        val icon: Painter,
        val contentDescription: String,
    ) : UiButtonType(
        backgroundColor = White,
        contentColor = IndigoBlue
    )
}