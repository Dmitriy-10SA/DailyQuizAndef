package com.andef.dailyquiz.quiz.presentation.main

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

sealed class CollectScreenStep {
    data object Filter : CollectScreenStep()
    data class Quiz(
        val questions: List<Question>,
        val shuffledAnswers: List<List<String>>,
        val category: QuizCategory,
        val difficulty: QuizDifficulty
    ) : CollectScreenStep()

    data class Result(
        val correctAnsCnt: Int,
        val userAnswers: Map<Int, String>,
        val questions: List<Question>
    ) : CollectScreenStep()
}