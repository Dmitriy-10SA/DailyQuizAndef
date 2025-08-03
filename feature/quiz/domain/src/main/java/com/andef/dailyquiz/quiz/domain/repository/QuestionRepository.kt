package com.andef.dailyquiz.quiz.domain.repository

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

/**
 * Интерфейс репозитория для работы с вопросами викторины.
 */
interface QuestionRepository {
    /**
     * Загрузка списка вопросов с заданными параметрами.
     *
     * @param amount Количество запрашиваемых вопросов (по умолчанию 5).
     * @param category Категория викторины.
     * @param difficulty Уровень сложности.
     *
     * @return Список вопросов [Question].
     */
    suspend fun loadQuestions(
        amount: Int = 5,
        category: QuizCategory,
        difficulty: QuizDifficulty
    ): List<Question>
}