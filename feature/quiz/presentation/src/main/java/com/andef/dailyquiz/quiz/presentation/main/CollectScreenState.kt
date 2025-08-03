package com.andef.dailyquiz.quiz.presentation.main

/**
 * Состояние экрана CollectScreen.
 *
 * @property step Текущий шаг (Filter, Quiz, Result).
 */
data class CollectScreenState(
    val step: CollectScreenStep = CollectScreenStep.Filter
)
