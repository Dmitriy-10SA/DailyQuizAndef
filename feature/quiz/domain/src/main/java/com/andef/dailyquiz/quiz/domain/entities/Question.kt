package com.andef.dailyquiz.quiz.domain.entities

/**
 * Доменная модель вопроса викторины.
 *
 * @property text Текст вопроса.
 * @property correctAnswer Правильный ответ.
 * @property incorrectAnswers Список неправильных ответов.
 */
data class Question(
    val text: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)