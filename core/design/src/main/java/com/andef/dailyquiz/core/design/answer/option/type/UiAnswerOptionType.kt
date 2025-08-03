package com.andef.dailyquiz.core.design.answer.option.type

import androidx.compose.ui.graphics.Color
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.Green
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.Red
import com.andef.dailyquiz.core.design.answer.option.ui.UiAnswerOption

/**
 * Тип для UI-компонента варианта ответа.
 *
 * Используется для визуального отображения состояния ответа:
 * верный, неверный, выбранный или стандартный.
 *
 * @property iconResId ресурс иконки, отображаемой слева от текста
 * @property borderColor цвет рамки вокруг компонента (или null, если без рамки)
 *
 * @see UiAnswerOption
 */
sealed class UiAnswerOptionType(val iconResId: Int, val borderColor: Color?) {
    /** Неверный вариант ответа (показывается после выбора) */
    data object Wrong : UiAnswerOptionType(
        iconResId = R.drawable.wrong_radio_button,
        borderColor = Red
    )

    /** Верный вариант ответа (показывается после проверки) */
    data object Right : UiAnswerOptionType(
        iconResId = R.drawable.right_radio_button,
        borderColor = Green
    )

    /** Выбранный пользователем вариант, до проверки правильности */
    data object Selected : UiAnswerOptionType(
        iconResId = R.drawable.selected_radio_button,
        borderColor = DeepPurple
    )

    /** Вариант по умолчанию (не выбран, не проверен) */
    data object Default : UiAnswerOptionType(
        iconResId = R.drawable.default_radio_button,
        borderColor = null
    )
}