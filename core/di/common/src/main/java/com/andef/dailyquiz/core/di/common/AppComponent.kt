package com.andef.dailyquiz.core.di.common

import android.app.Application
import androidx.activity.ComponentActivity
import com.andef.dailyquiz.core.di.common.db.QuizDaoModule
import com.andef.dailyquiz.core.di.common.db.QuizRepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        //core:data
        QuizDaoModule::class,
        QuizRepositoryModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(activity: ComponentActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}