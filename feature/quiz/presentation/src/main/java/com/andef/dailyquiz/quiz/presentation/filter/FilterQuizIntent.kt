package com.andef.dailyquiz.quiz.presentation.filter

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

sealed class FilterQuizIntent {
    data class QuizCategoryChange(val category: QuizCategory) : FilterQuizIntent()
    data class QuizCategoryExpandedChange(val expanded: Boolean) : FilterQuizIntent()
    data class QuizDifficultyChange(val difficulty: QuizDifficulty) : FilterQuizIntent()
    data class QuizDifficultyExpandedChange(val expanded: Boolean) : FilterQuizIntent()
    data class LoadQuestions(
        val onSuccess: (List<Question>) -> Unit,
        val onError: (Int) -> Unit
    ) : FilterQuizIntent()
}