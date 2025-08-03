package com.andef.dailyquiz.history.presentation

/**
 * Намерения (интенты) экрана истории. Используются для управления логикой в ViewModel.
 */
sealed class HistoryScreenIntent {
    /**
     * Подписка на получение списка завершённых викторин.
     * Вызывается при первом запуске экрана или после ошибки загрузки.
     */
    data object SubscribeForQuizzes : HistoryScreenIntent()

    /**
     * Удаление конкретной викторины по ID.
     *
     * @param id ID викторины, которую нужно удалить.
     * @param onSuccess Колбэк, вызываемый при успешном удалении.
     * @param onError Колбэк, вызываемый при ошибке удаления, с передачей ID строки ресурса ошибки.
     */
    data class DeleteQuiz(
        val id: Long,
        val onSuccess: () -> Unit,
        val onError: (Int) -> Unit
    ) : HistoryScreenIntent()

    /**
     * Изменение видимости диалога подтверждения удаления.
     *
     * @param isVisible Флаг отображения диалога.
     */
    data class ChangeSuccessDeleteDialogVisible(val isVisible: Boolean) : HistoryScreenIntent()
}