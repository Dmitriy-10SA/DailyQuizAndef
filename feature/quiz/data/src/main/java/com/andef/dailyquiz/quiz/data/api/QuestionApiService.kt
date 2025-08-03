package com.andef.dailyquiz.quiz.data.api

import com.andef.dailyquiz.quiz.data.dto.QuestionApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApiService {
    @GET("api.php")
    suspend fun loadQuestions(
        @Query("amount") amount: Int = 5,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String = "multiple"
    ): QuestionApiResponseDto
}