package com.andef.dailyquiz.quiz.presentation.main

import com.andef.dailyquiz.quiz.domain.entities.Question

sealed class CollectScreenStep {
    data object Filter : CollectScreenStep()
    data class Quiz(
        val questions: List<Question>,
        val shuffledAnswers: List<List<String>>
    ) : CollectScreenStep()

    data object Result : CollectScreenStep()
}