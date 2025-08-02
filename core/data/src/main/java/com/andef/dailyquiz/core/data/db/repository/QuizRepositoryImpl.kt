package com.andef.dailyquiz.core.data.db.repository

import com.andef.dailyquiz.core.data.db.dao.QuizDao
import com.andef.dailyquiz.core.data.db.mapper.QuizMapper
import com.andef.dailyquiz.core.domain.entites.Quiz
import com.andef.dailyquiz.core.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao,
    private val mapper: QuizMapper
) : QuizRepository {
    override fun getQuizzes(): Flow<List<Quiz>> {
        return dao.getQuizzes().map { quizDboList ->
            quizDboList.map { quizDbo ->
                mapper.toQuiz(quizDbo)
            }
        }
    }

    override suspend fun addQuiz(quiz: Quiz): Long {
        return dao.addQuiz(mapper.toQuizDbo(quiz))
    }

    override suspend fun deleteQuiz(id: Long) {
        dao.deleteQuiz(id)
    }
}