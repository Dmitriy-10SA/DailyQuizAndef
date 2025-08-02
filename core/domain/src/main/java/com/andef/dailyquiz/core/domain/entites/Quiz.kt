package com.andef.dailyquiz.core.domain.entites

import java.time.LocalDate
import java.time.LocalTime

data class Quiz(
    val id: Long,
    val correctAnswersCnt: Int,
    val date: LocalDate,
    val time: LocalTime,
    val category: QuizCategory,
    val difficulty: QuizDifficulty
)
