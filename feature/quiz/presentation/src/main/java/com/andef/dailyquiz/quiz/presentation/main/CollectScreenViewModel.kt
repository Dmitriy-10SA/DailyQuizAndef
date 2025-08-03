package com.andef.dailyquiz.quiz.presentation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * ViewModel для управления состоянием и переходами между этапами на экране CollectScreen.
 */
class CollectScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(CollectScreenState())
    val state: StateFlow<CollectScreenState> = _state

    fun send(intent: CollectScreenIntent) {
        when (intent) {
            is CollectScreenIntent.StepChange -> {
                _state.value = _state.value.copy(step = intent.step)
            }
        }
    }
}