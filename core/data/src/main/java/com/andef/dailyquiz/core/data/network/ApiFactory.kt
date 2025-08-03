package com.andef.dailyquiz.core.data.network

import com.andef.dailyquiz.quiz.data.api.QuestionApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Фабрика для получения QuestionApiService
 *
 * @property QuestionApiService ApiService для получения вопросов к викторине
 */
class ApiFactory {
    companion object {
        private const val BASE_URL = "https://opentdb.com/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val questionApiService: QuestionApiService = retrofit.create(QuestionApiService::class.java)
    }
}