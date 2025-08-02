package com.andef.dailyquiz.quiz.presentation.quiz

import com.andef.dailyquiz.quiz.domain.entities.Question

sealed class QuizScreenIntent {
    data class InitQuestionsAndTimer(
        val questions: List<Question>,
        val shuffledAnswers: List<List<String>>,
        val onQuizFinished: (Boolean) -> Unit
    ) : QuizScreenIntent()

    data class AnswerChoose(val answer: String) : QuizScreenIntent()
    data object NextClick : QuizScreenIntent()
}