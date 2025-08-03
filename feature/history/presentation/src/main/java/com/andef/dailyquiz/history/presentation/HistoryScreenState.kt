package com.andef.dailyquiz.history.presentation

import com.andef.dailyquiz.core.domain.entites.Quiz

/**
 * Состояние UI для экрана истории.
 *
 * @property quizzes Список завершённых викторин.
 * @property successDeleteDialog Флаг, показывающий, должен ли отображаться диалог после удаления.
 * @property isLoading Признак, что данные загружаются.
 * @property isError Признак ошибки при загрузке данных.
 */
data class HistoryScreenState(
    val quizzes: List<Quiz> = listOf<Quiz>(),
    val successDeleteDialog: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
