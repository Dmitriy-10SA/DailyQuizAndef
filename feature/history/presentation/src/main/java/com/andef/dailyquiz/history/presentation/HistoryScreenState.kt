package com.andef.dailyquiz.history.presentation

import com.andef.dailyquiz.core.domain.entites.Quiz

data class HistoryScreenState(
    val quizzes: List<Quiz> = listOf<Quiz>(),
    val successDeleteDialog: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
