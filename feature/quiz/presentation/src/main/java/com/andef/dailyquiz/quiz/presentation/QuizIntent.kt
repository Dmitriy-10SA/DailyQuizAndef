package com.andef.dailyquiz.quiz.presentation

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty

sealed class QuizIntent {
    data class QuizCategoryChange(val category: QuizCategory) : QuizIntent()
    data class QuizCategoryExpandedChange(val expanded: Boolean) : QuizIntent()
    data class QuizDifficultyChange(val difficulty: QuizDifficulty) : QuizIntent()
    data class QuizDifficultyExpandedChange(val expanded: Boolean) : QuizIntent()
}