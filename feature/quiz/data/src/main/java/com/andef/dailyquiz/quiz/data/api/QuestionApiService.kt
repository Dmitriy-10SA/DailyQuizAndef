package com.andef.dailyquiz.quiz.data.api

import com.andef.dailyquiz.quiz.data.dto.QuestionApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Сервис API для загрузки вопросов к викторине с удалённого сервера.
 */
interface QuestionApiService {
    /**
     * Запрос на получение списка вопросов.
     *
     * @param amount Количество вопросов для загрузки (по умолчанию — 5).
     * @param category ID категории вопросов.
     * @param difficulty Уровень сложности (например, "easy", "medium", "hard").
     * @param type Тип вопроса (по умолчанию — "multiple").
     *
     * @return Ответ API в виде DTO-объекта [QuestionApiResponseDto].
     */
    @GET("api.php")
    suspend fun loadQuestions(
        @Query("amount") amount: Int = 5,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String = "multiple"
    ): QuestionApiResponseDto
}