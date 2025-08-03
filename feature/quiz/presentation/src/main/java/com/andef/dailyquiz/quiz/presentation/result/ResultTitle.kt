package com.andef.dailyquiz.quiz.presentation.result

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.dailyquiz.core.design.R

/**
 * Получить заголовок результата в зависимости от количества правильных ответов.
 *
 * Используется для отображения оценки результата квиза.
 *
 * @param correctAnsCnt Количество правильных ответов.
 * @return Локализованная строка-заголовок.
 *
 * @throws IllegalArgumentException Если количество ответов вне диапазона [0..5].
 */
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

/**
 * Получить описание результата в зависимости от количества правильных ответов.
 *
 * Предоставляет мотивационное или информативное сообщение после квиза.
 *
 * @param correctAnsCnt Количество правильных ответов.
 * @return Локализованное описание результата.
 *
 * @throws IllegalArgumentException Если количество ответов вне диапазона [0..5].
 */
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