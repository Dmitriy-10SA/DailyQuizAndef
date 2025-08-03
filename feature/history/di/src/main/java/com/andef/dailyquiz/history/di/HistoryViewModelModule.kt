package com.andef.dailyquiz.history.di

import androidx.lifecycle.ViewModel
import com.andef.dailyquiz.core.di.viewmodel.ViewModelKey
import com.andef.dailyquiz.history.presentation.HistoryScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * DI для HistoryViewModel
 */
@Module
interface HistoryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HistoryScreenViewModel::class)
    fun bindHistoryScreenViewModel(impl: HistoryScreenViewModel): ViewModel
}