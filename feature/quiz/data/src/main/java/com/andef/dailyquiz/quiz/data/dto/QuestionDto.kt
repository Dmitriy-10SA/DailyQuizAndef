package com.andef.dailyquiz.quiz.data.dto

import com.google.gson.annotations.SerializedName

data class QuestionDto(
    @SerializedName("question")
    val text: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>
)
