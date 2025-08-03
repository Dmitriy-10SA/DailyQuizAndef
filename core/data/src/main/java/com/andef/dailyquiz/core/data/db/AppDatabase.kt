package com.andef.dailyquiz.core.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.dailyquiz.core.data.db.dao.QuizDao
import com.andef.dailyquiz.core.data.db.dbo.QuizDbo

/**
 * БД для приложения
 *
 * @property quizDao DAO для сущности викторина
 *
 * @see QuizDbo
 */
@Database(entities = [QuizDbo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val quizDao: QuizDao

    companion object {
        private const val DB_NAME = "daily_quiz_db"

        private var instance: AppDatabase? = null
        fun getInstance(application: Application): AppDatabase {
            if (instance != null) return instance!!
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}