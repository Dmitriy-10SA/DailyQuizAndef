package com.andef.dailyquiz.quiz.data.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO-модель ответа API при получении вопросов.
 *
 * @property responseCode Код ответа API.
 * @property results Список полученных вопросов в виде DTO [QuestionDto].
 */
data class QuestionApiResponseDto(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val results: List<QuestionDto>
)
