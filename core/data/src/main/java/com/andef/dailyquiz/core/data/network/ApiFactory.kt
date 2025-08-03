package com.andef.dailyquiz.core.data.network

import com.andef.dailyquiz.quiz.data.api.QuestionApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Фабрика для получения сетевого сервиса [QuestionApiService].
 *
 * Конфигурирует Retrofit с базовым URL и конвертером Gson.
 */
class ApiFactory {
    companion object {
        private const val BASE_URL = "https://opentdb.com/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /**
         * Сервис API для получения вопросов викторины.
         */
        val questionApiService: QuestionApiService = retrofit.create(QuestionApiService::class.java)
    }
}