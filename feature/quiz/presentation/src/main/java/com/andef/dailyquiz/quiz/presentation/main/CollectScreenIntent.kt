package com.andef.dailyquiz.quiz.presentation.main

sealed class CollectScreenIntent {
    data class StepChange(
        val step: CollectScreenStep
    ) : CollectScreenIntent()
}