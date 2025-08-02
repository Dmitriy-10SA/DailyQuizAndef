package com.andef.dailyquiz.quiz.domain.repository

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

interface QuestionRepository {
    suspend fun loadQuestions(
        amount: Int = 5,
        category: QuizCategory,
        difficulty: QuizDifficulty
    ): List<Question>
}