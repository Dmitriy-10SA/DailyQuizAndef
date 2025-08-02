package com.andef.dailyquiz.quiz.di

import androidx.lifecycle.ViewModel
import com.andef.dailyquiz.core.di.viewmodel.ViewModelKey
import com.andef.dailyquiz.quiz.presentation.QuizViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface QuizViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    fun bindQuizViewModel(impl: QuizViewModel): ViewModel
}