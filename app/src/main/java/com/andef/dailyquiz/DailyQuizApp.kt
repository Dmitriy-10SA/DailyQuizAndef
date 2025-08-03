package com.andef.dailyquiz

import android.app.Application
import com.andef.dailyquiz.core.di.common.DaggerAppComponent

class DailyQuizApp : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}