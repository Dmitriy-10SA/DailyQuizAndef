package com.andef.dailyquiz.quiz.presentation.filter

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

/**
 * Интенты для управления состоянием экрана фильтрации викторины.
 */
sealed class FilterQuizIntent {
    /**
     * Устанавливает выбранную категорию викторины.
     */
    data class QuizCategoryChange(val category: QuizCategory) : FilterQuizIntent()

    /**
     * Изменяет состояние раскрытия меню категорий.
     */
    data class QuizCategoryExpandedChange(val expanded: Boolean) : FilterQuizIntent()

    /**
     * Устанавливает выбранную сложность квиза.
     */
    data class QuizDifficultyChange(val difficulty: QuizDifficulty) : FilterQuizIntent()

    /**
     * Изменяет состояние раскрытия меню сложности.
     */
    data class QuizDifficultyExpandedChange(val expanded: Boolean) : FilterQuizIntent()

    /**
     * Загружает список вопросов с сервера.
     *
     * @param onSuccess Колбэк при успешной загрузке: возвращает список вопросов, список перемешанных ответов, выбранную категорию и сложность.
     * @param onError Колбэк при ошибке: принимает ресурс строки ошибки.
     */
    data class LoadQuestions(
        val onSuccess: (List<Question>, List<List<String>>, QuizCategory, QuizDifficulty) -> Unit,
        val onError: (Int) -> Unit
    ) : FilterQuizIntent()
}