package com.andef.dailyquiz.quiz.data.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO-модель вопроса викторины, полученного от API.
 *
 * @property text Текст вопроса.
 * @property correctAnswer Правильный ответ.
 * @property incorrectAnswers Список неправильных ответов.
 */
data class QuestionDto(
    @SerializedName("question")
    val text: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>
)
