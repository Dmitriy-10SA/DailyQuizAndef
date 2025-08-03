package com.andef.dailyquiz.quiz.di

import com.andef.dailyquiz.core.data.network.ApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class QuestionApiServiceModule {
    @Provides
    @Singleton
    fun provideQuestionApiService() = ApiFactory.questionApiService
}