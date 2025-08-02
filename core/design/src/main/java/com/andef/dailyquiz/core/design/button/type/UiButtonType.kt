package com.andef.dailyquiz.core.design.button.type

import androidx.compose.ui.graphics.Color
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.White

sealed class UiButtonType(val backgroundColor: Color, val contentColor: Color) {
    data object Main : UiButtonType(backgroundColor = IndigoBlue, contentColor = White)
    data object Secondary : UiButtonType(backgroundColor = White, contentColor = DeepPurple)
    data object History : UiButtonType(backgroundColor = White, contentColor = IndigoBlue)
}