package com.andef.dailyquiz.quiz.presentation.main

import com.andef.dailyquiz.quiz.domain.entities.Question

sealed class CollectScreenStep {
    data object Filter : CollectScreenStep()
    data class Quiz(val questions: List<Question>) : CollectScreenStep()
    data object Result : CollectScreenStep()
}