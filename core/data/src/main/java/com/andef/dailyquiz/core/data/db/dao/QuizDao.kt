package com.andef.dailyquiz.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andef.dailyquiz.core.data.db.dbo.QuizDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Query("""
    SELECT * FROM quiz
    ORDER BY finished_date DESC, finished_time DESC
    """)
    fun getQuizzes(): Flow<List<QuizDbo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quizDbo: QuizDbo): Long

    @Query("DELETE FROM quiz WHERE id = :id")
    suspend fun deleteQuiz(id: Long)
}