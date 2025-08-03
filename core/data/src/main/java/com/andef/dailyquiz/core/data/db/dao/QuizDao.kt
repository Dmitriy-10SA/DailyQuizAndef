package com.andef.dailyquiz.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andef.dailyquiz.core.data.db.dbo.QuizDbo
import kotlinx.coroutines.flow.Flow

/**
 * DAO для сущности викторины из БД.
 *
 * Методы для получения, добавления и удаления викторин.
 *
 * @see QuizDbo
 */
@Dao
interface QuizDao {
    /**
     * Получение всех пройденных викторин из БД (Flow списка).
     */
    @Query("SELECT * FROM quiz ORDER BY finished_date ASC, finished_time ASC")
    fun getQuizzes(): Flow<List<QuizDbo>>

    /**
     * Добавление викторины в БД.
     *
     * @param quizDbo сущность викторины для добавления
     * @return id добавленной записи
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quizDbo: QuizDbo): Long

    /**
     * Удаление викторины из БД по идентификатору.
     *
     * @param id идентификатор викторины для удаления
     */
    @Query("DELETE FROM quiz WHERE id = :id")
    suspend fun deleteQuiz(id: Long)
}