package com.andef.dailyquiz.core.design.button.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.button.ui.UiButton

/**
 * Тип кнопки
 *
 * @property Main главная кнопка (основная) - Active/Inactive в Figma
 * @property Secondary второстепенная кнопка - White/Inactive в Figma
 * @property WithIcon с иконкой справа - HistoryButton в Figma
 *
 * @see UiButton
 */
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