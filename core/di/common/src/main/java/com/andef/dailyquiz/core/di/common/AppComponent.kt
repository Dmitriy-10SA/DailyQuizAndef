package com.andef.dailyquiz.core.di.common

import android.app.Application
import androidx.activity.ComponentActivity
import com.andef.dailyquiz.core.di.common.db.QuizDaoModule
import com.andef.dailyquiz.core.di.common.db.QuizRepositoryModule
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.quiz.di.QuestionApiServiceModule
import com.andef.dailyquiz.quiz.di.QuestionRepositoryModule
import com.andef.dailyquiz.quiz.di.QuizViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        //core:data
        QuizDaoModule::class,
        QuizRepositoryModule::class,

        //feature:quiz
        QuizViewModelModule::class,
        QuestionRepositoryModule::class,
        QuestionApiServiceModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(activity: ComponentActivity)

    val viewModelFactory: ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}