package com.andef.dailyquiz.core.di.common.db

import android.app.Application
import com.andef.dailyquiz.core.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.andef.dailyquiz.core.data.db.dao.QuizDao

/**
 * DI для DAO по работе в викторинами
 *
 * @see QuizDao
 * @see AppDatabase
 */
@Module
class QuizDaoModule {
    @Provides
    @Singleton
    fun provideQuizDao(application: Application) = AppDatabase.getInstance(application).quizDao
}