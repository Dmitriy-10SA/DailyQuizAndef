package com.andef.dailyquiz.core.data.db.mapper

import com.andef.dailyquiz.core.data.db.dbo.QuizDbo
import com.andef.dailyquiz.core.domain.entites.Quiz
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

/**
 * Маппер для сущности викторина
 *
 * @property toQuizDbo маппинг из domain-сущности в data-сущность
 * @property toQuizDbo маппинг из data-сущности в domain-сущность
 *
 * @see QuizDbo
 * @see Quiz
 */
class QuizMapper @Inject constructor() {
    fun toQuizDbo(quiz: Quiz) = QuizDbo(
        id = quiz.id,
        correctAnswersCnt = quiz.correctAnswersCnt,
        date = quiz.date.toString(),
        time = quiz.time.toString(),
        category = quiz.category,
        difficulty = quiz.difficulty
    )

    fun toQuiz(quizDbo: QuizDbo) = Quiz(
        id = quizDbo.id,
        correctAnswersCnt = quizDbo.correctAnswersCnt,
        date = LocalDate.parse(quizDbo.date),
        time = LocalTime.parse(quizDbo.time),
        category = quizDbo.category,
        difficulty = quizDbo.difficulty
    )
}