package com.andef.dailyquiz.core.data.db.mapper

import com.andef.dailyquiz.core.data.db.dbo.QuizDbo
import com.andef.dailyquiz.core.domain.entites.Quiz
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

/**
 * Маппер для преобразования между domain- и data-сущностями викторины.
 *
 * @see QuizDbo
 * @see Quiz
 */
class QuizMapper @Inject constructor() {
    /**
     * Преобразование domain-сущности [Quiz] в data-сущность [QuizDbo].
     */
    fun toQuizDbo(quiz: Quiz) = QuizDbo(
        id = quiz.id,
        correctAnswersCnt = quiz.correctAnswersCnt,
        date = quiz.date.toString(),
        time = quiz.time.toString(),
        category = quiz.category,
        difficulty = quiz.difficulty
    )

    /**
     * Преобразование data-сущности [QuizDbo] в domain-сущность [Quiz].
     */
    fun toQuiz(quizDbo: QuizDbo) = Quiz(
        id = quizDbo.id,
        correctAnswersCnt = quizDbo.correctAnswersCnt,
        date = LocalDate.parse(quizDbo.date),
        time = LocalTime.parse(quizDbo.time),
        category = quizDbo.category,
        difficulty = quizDbo.difficulty
    )
}