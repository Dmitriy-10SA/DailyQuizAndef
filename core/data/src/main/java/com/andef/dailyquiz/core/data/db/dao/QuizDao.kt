package com.andef.dailyquiz.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andef.dailyquiz.core.data.db.dbo.QuizDbo
import kotlinx.coroutines.flow.Flow

/**
 * @property getQuizzes получение всех пройденных викторин из БД (flow)
 * @property addQuiz добавление викторины в БД
 * @property deleteQuiz удаление викторины из БД
 */
@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz ORDER BY finished_date ASC, finished_time ASC")
    fun getQuizzes(): Flow<List<QuizDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quizDbo: QuizDbo): Long

    @Query("DELETE FROM quiz WHERE id = :id")
    suspend fun deleteQuiz(id: Long)
}