package com.andef.dailyquiz.core.design.button.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.button.ui.UiButton

/**
 * Типы кнопок, используемых в UI.
 *
 * Определяет цвет фона и содержимого, а также наличие иконки.
 *
 * @property backgroundColor цвет фона кнопки
 * @property contentColor цвет текста/иконок кнопки
 *
 * @see UiButton
 */
sealed class UiButtonType(val backgroundColor: Color, val contentColor: Color) {
    /** Главная кнопка — активная фиолетовая (Active в Figma) */
    data object Main : UiButtonType(backgroundColor = IndigoBlue, contentColor = White)

    /** Второстепенная кнопка — белая с фиолетовым текстом (White в Figma) */
    data object Secondary : UiButtonType(backgroundColor = White, contentColor = DeepPurple)

    /**
     * Кнопка с иконкой справа — используется, например, в истории.
     *
     * @property icon иконка, отображаемая справа от текста
     * @property contentDescription описание иконки
     */
    data class WithIcon(
        val icon: Painter,
        val contentDescription: String,
    ) : UiButtonType(
        backgroundColor = White,
        contentColor = IndigoBlue
    )
}