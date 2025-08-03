package com.andef.dailyquiz.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        }
    }

    private fun deleteQuiz(quizId: Long, onSuccess: () -> Unit, onError: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                withContext(Dispatchers.IO) { deleteQuizUseCase.invoke(quizId) }
                onSuccess()
            } catch (_: Exception) {
                onError(com.andef.dailyquiz.core.design.R.string.error_toast_msg)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

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