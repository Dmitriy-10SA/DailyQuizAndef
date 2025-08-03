package com.andef.dailyquiz.core.domain.repository

import com.andef.dailyquiz.core.domain.entites.Quiz
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для работы с сущностью викторины.
 *
 * Выполняет операции получения, добавления и удаления викторин
 *
 * @see Quiz
 */
interface QuizRepository {
    fun getQuizzes(): Flow<List<Quiz>>
    suspend fun addQuiz(quiz: Quiz): Long
    suspend fun deleteQuiz(id: Long)
}