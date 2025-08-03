package com.andef.dailyquiz.quiz.presentation.quiz

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

sealed class QuizScreenIntent {
    data class InitQuestionsAndTimer(
        val questions: List<Question>,
        val category: QuizCategory,
        val difficulty: QuizDifficulty,
        val shuffledAnswers: List<List<String>>,
        val onQuizFinished: (Boolean) -> Unit
    ) : QuizScreenIntent()

    data class AnswerChoose(val answer: String) : QuizScreenIntent()
    data object NextClick : QuizScreenIntent()
    data class SaveResults(
        val onSuccess: (Int, Map<Int, String>, List<Question>) -> Unit,
        val onError: () -> Unit
    ) : QuizScreenIntent()

    data class ChangeErrorDialogVisible(val isVisible: Boolean) : QuizScreenIntent()
}