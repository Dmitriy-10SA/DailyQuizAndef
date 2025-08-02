package com.andef.dailyquiz.core.design.answer.option.type

import androidx.compose.ui.graphics.Color
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.Green
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.Red

sealed class UiAnswerOptionType(val iconResId: Int, val borderColor: Color?) {
    data object Wrong : UiAnswerOptionType(
        iconResId = R.drawable.wrong_radio_button,
        borderColor = Red
    )

    data object Right : UiAnswerOptionType(
        iconResId = R.drawable.right_radio_button,
        borderColor = Green
    )

    data object Selected : UiAnswerOptionType(
        iconResId = R.drawable.selected_radio_button,
        borderColor = DeepPurple
    )

    data object Default : UiAnswerOptionType(
        iconResId = R.drawable.default_radio_button,
        borderColor = null
    )
}