package com.andef.dailyquiz.quiz.domain.entities

data class Question(
    val text: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)