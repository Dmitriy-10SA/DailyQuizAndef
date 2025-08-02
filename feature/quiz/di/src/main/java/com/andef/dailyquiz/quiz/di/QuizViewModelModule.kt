package com.andef.dailyquiz.quiz.di

import androidx.lifecycle.ViewModel
import com.andef.dailyquiz.core.di.viewmodel.ViewModelKey
import com.andef.dailyquiz.quiz.presentation.main.CollectScreenViewModel
import com.andef.dailyquiz.quiz.presentation.filter.FilterQuizViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface QuizViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CollectScreenViewModel::class)
    fun bindCollectScreenViewModel(impl: CollectScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterQuizViewModel::class)
    fun bindFilterQuizViewModel(impl: FilterQuizViewModel): ViewModel
}