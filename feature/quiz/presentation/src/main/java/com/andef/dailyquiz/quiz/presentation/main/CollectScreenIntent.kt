package com.andef.dailyquiz.quiz.presentation.main

/**
 * Интенты (события), управляющие логикой главного экрана викторины.
 */
sealed class CollectScreenIntent {
    /**
     * Изменение текущего этапа (Filter → Quiz → Result).
     *
     * @param step Новый шаг [CollectScreenStep].
     */
    data class StepChange(
        val step: CollectScreenStep
    ) : CollectScreenIntent()
}