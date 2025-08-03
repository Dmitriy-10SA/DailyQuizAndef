package com.andef.dailyquiz.quiz.presentation.result

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.dailyquiz.core.design.R

@Composable
fun getResultTitle(correctAnsCnt: Int): String {
    return when (correctAnsCnt) {
        5 -> stringResource(R.string.result_5_title)
        4 -> stringResource(R.string.result_4_title)
        3 -> stringResource(R.string.result_3_title)
        2 -> stringResource(R.string.result_2_title)
        1 -> stringResource(R.string.result_1_title)
        0 -> stringResource(R.string.result_0_title)
        else -> throw IllegalArgumentException("$correctAnsCnt not implemented")
    }
}

@Composable
fun getResultDescription(correctAnsCnt: Int): String {
    return when (correctAnsCnt) {
        5 -> stringResource(R.string.result_5_description)
        4 -> stringResource(R.string.result_4_description)
        3 -> stringResource(R.string.result_3_description)
        2 -> stringResource(R.string.result_2_description)
        1 -> stringResource(R.string.result_1_description)
        0 -> stringResource(R.string.result_0_description)
        else -> throw IllegalArgumentException("$correctAnsCnt not implemented")
    }
}