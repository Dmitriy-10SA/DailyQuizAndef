package com.andef.dailyquiz.quiz.presentation

sealed class QuizStep {
    data object Filter : QuizStep()
    data object Quiz : QuizStep()
    data object Result : QuizStep()
}