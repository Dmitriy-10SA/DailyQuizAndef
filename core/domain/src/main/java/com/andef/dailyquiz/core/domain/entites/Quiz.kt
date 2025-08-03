package com.andef.dailyquiz.core.domain.entites

import java.time.LocalDate
import java.time.LocalTime

/**
 * Сущность викторина.
 *
 * @property id идентификатор (автоинкрементируемый)
 * @property correctAnswersCnt количество правильных ответов пользователя в викторине
 * @property date дата завершения викторины
 * @property time время завершения викторины
 * @property category категория викторины
 * @property difficulty сложность викторины
 *
 * @see QuizCategory
 * @see QuizDifficulty
 */
data class Quiz(
    val id: Long,
    val correctAnswersCnt: Int,
    val date: LocalDate,
    val time: LocalTime,
    val category: QuizCategory,
    val difficulty: QuizDifficulty
)
