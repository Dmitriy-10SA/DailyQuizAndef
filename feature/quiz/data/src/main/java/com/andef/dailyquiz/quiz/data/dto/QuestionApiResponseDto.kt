package com.andef.dailyquiz.quiz.data.dto

import com.google.gson.annotations.SerializedName

data class QuestionApiResponseDto(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val results: List<QuestionDto>
)
