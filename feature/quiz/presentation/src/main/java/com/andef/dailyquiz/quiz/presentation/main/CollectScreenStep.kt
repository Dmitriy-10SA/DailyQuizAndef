package com.andef.dailyquiz.quiz.presentation.main

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

/**
 * Возможные этапы экрана сбора викторины.
 */
sealed class CollectScreenStep {
    /**
     * Этап выбора параметров викторины (категория, сложность).
     */
    data object Filter : CollectScreenStep()

    /**
     * Этап прохождения викторины.
     *
     * @param questions Список вопросов.
     * @param shuffledAnswers Список перемешанных ответов (для UI).
     * @param category Категория викторины.
     * @param difficulty Уровень сложности.
     */
    data class Quiz(
        val questions: List<Question>,
        val shuffledAnswers: List<List<String>>,
        val category: QuizCategory,
        val difficulty: QuizDifficulty
    ) : CollectScreenStep()

    /**
     * Этап отображения результатов викторины.
     *
     * @param correctAnsCnt Количество правильных ответов.
     * @param userAnswers Ответы пользователя (ключ — индекс вопроса).
     * @param questions Список вопросов.
     * @param shuffledAnswers Перемешанные ответы, соответствующие вопросам.
     */
    data class Result(
        val correctAnsCnt: Int,
        val userAnswers: Map<Int, String>,
        val questions: List<Question>,
        val shuffledAnswers: List<List<String>>
    ) : CollectScreenStep()
}