package com.andef.dailyquiz.quiz.presentation.quiz

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

data class QuizScreenState(
    val currentQuestionIndex: Int = 0,
    val questions: List<Question> = listOf<Question>(),
    val shuffledAnswers: List<List<String>> = emptyList<List<String>>(),
    val showRightAnswer: Boolean = false,
    val userAnswers: Map<Int, String> = mapOf<Int, String>(),
    val category: QuizCategory? = null,
    val difficulty: QuizDifficulty? = null,
    val currentTimeSeconds: Int = 0,
    val quizFinished: Boolean = false,
    val nextButtonEnabled: Boolean = false,
    val errorDialogVisible: Boolean = false,
    val timeOverDialogVisible: Boolean = false,
    val isLoading: Boolean = false
) {
    companion object {
        const val TOTAL_TIME_SECONDS = 25
    }
}
