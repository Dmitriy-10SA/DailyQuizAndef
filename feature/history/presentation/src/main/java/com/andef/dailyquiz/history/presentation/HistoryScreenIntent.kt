package com.andef.dailyquiz.history.presentation

sealed class HistoryScreenIntent {
    data object SubscribeForQuizzes : HistoryScreenIntent()
    data class DeleteQuiz(
        val id: Long,
        val onSuccess: () -> Unit,
        val onError: (Int) -> Unit
    ) : HistoryScreenIntent()
}