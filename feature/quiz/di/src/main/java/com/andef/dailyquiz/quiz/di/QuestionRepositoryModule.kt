package com.andef.dailyquiz.quiz.di

import com.andef.dailyquiz.quiz.data.repository.QuestionRepositoryImpl
import com.andef.dailyquiz.quiz.domain.repository.QuestionRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface QuestionRepositoryModule {
    @Binds
    @Singleton
    fun bindQuestionRepository(impl: QuestionRepositoryImpl): QuestionRepository
}