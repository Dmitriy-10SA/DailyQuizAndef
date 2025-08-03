package com.andef.dailyquiz.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.dailyquiz.core.design.R
import com.andef.dayliquiz.history.domain.DeleteQuizUseCase
import com.andef.dayliquiz.history.domain.GetQuizzesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel для экрана истории завершённых викторин.
 *
 * Обрабатывает интенты, управляет состоянием и взаимодействует с use-case'ами.
 *
 * @property deleteQuizUseCase Use-case для удаления викторины.
 * @property getQuizzesUseCase Use-case для получения списка викторин.
 */
class HistoryScreenViewModel @Inject constructor(
    private val deleteQuizUseCase: DeleteQuizUseCase,
    private val getQuizzesUseCase: GetQuizzesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HistoryScreenState())
    val state: StateFlow<HistoryScreenState> = _state

    fun send(intent: HistoryScreenIntent) {
        when (intent) {
            is HistoryScreenIntent.DeleteQuiz -> {
                deleteQuiz(
                    quizId = intent.id,
                    onSuccess = intent.onSuccess,
                    onError = intent.onError
                )
            }

            HistoryScreenIntent.SubscribeForQuizzes -> {
                subscribeForQuizzes()
            }

            is HistoryScreenIntent.ChangeSuccessDeleteDialogVisible -> {
                _state.value = _state.value.copy(successDeleteDialog = intent.isVisible)
            }
        }
    }

    /**
     * Удаление викторины по ID.
     *
     * @param quizId ID викторины.
     * @param onSuccess Колбэк при успешном удалении.
     * @param onError Колбэк при ошибке, с ресурсом строки ошибки.
     */
    private fun deleteQuiz(quizId: Long, onSuccess: () -> Unit, onError: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                withContext(Dispatchers.IO) { deleteQuizUseCase.invoke(quizId) }
                onSuccess()
            } catch (_: Exception) {
                onError(R.string.error_toast_msg)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    /**
     * Подписка на поток викторин. Запускается один раз при инициализации,
     * либо при ошибке повторно (по запросу).
     */
    private var isFirstStart = true
    private var job: Job? = null
    private fun subscribeForQuizzes() {
        if (isFirstStart || _state.value.isError) {
            isFirstStart = false
            job?.cancel()
            job = viewModelScope.launch {
                getQuizzesUseCase()
                    .onStart {
                        _state.value = _state.value.copy(isLoading = true, isError = false)
                    }
                    .catch {
                        _state.value = _state.value.copy(
                            quizzes = listOf(),
                            isError = true,
                            isLoading = false
                        )
                    }
                    .collect {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isError = false,
                            quizzes = it
                        )
                    }
            }
        }
    }

    init {
        subscribeForQuizzes()
    }
}