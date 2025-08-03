package com.andef.dailyquiz.core.di.common.db

import com.andef.dailyquiz.core.data.db.repository.QuizRepositoryImpl
import com.andef.dailyquiz.core.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * DI для репозитория по работе с викторинами
 *
 * @see QuizRepositoryImpl
 * @see QuizRepository
 */
@Module
interface QuizRepositoryModule {
    @Binds
    @Singleton
    fun bindQuizRepository(impl: QuizRepositoryImpl): QuizRepository
}