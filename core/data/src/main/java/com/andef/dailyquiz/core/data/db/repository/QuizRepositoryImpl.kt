package com.andef.dailyquiz.core.data.db.repository

import com.andef.dailyquiz.core.data.db.dao.QuizDao
import com.andef.dailyquiz.core.data.db.mapper.QuizMapper
import com.andef.dailyquiz.core.domain.entites.Quiz
import com.andef.dailyquiz.core.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Репозиторий для работы с сущностью викторины из БД.
 *
 * Выполняет операции получения, добавления и удаления викторин
 * с использованием DAO и маппера.
 *
 * @see QuizDao
 * @see QuizMapper
 */
class QuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao,
    private val mapper: QuizMapper
) : QuizRepository {
    /**
     * Получение всех пройденных викторин (Flow списка).
     */
    override fun getQuizzes(): Flow<List<Quiz>> {
        return dao.getQuizzes().map { quizDboList ->
            quizDboList.map { quizDbo ->
                mapper.toQuiz(quizDbo)
            }
        }
    }

    /**
     * Добавление викторины в БД.
     *
     * @param quiz объект викторины для добавления
     * @return id добавленной записи
     */
    override suspend fun addQuiz(quiz: Quiz): Long {
        return dao.addQuiz(mapper.toQuizDbo(quiz))
    }

    /**
     * Удаление викторины из БД по идентификатору.
     *
     * @param id идентификатор викторины
     */
    override suspend fun deleteQuiz(id: Long) {
        dao.deleteQuiz(id)
    }
}