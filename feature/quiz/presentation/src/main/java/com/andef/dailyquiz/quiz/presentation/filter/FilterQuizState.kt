package com.andef.dailyquiz.quiz.presentation.filter

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty

/**
 * Состояние экрана фильтрации квиза.
 *
 * Используется [FilterQuizViewModel] для управления UI-состоянием.
 */
data class FilterQuizState(
    val quizCategory: QuizCategory? = null,
    val quizCategoryExpanded: Boolean = false,
    val quizDifficulty: QuizDifficulty? = null,
    val quizDifficultyExpanded: Boolean = false,
    val furtherButtonEnabled: Boolean = false,
    val isLoading: Boolean = false
)
