package com.andef.dailyquiz.core.domain.repository

import com.andef.dailyquiz.core.domain.entites.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuizzes(): Flow<List<Quiz>>
    suspend fun addQuiz(quiz: Quiz): Long
    suspend fun deleteQuiz(id: Long)
}