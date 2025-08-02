package com.andef.dailyquiz.quiz.presentation.filter

import androidx.lifecycle.ViewModel
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FilterQuizViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(FilterQuizState())
    val state: StateFlow<FilterQuizState> = _state

    fun send(intent: FilterQuizIntent) {
        when (intent) {
            is FilterQuizIntent.QuizCategoryChange -> {
                stateChange(quizCategory = intent.category)
            }

            is FilterQuizIntent.QuizCategoryExpandedChange -> {
                stateChange(quizCategoryExpanded = intent.expanded)
            }

            is FilterQuizIntent.QuizDifficultyChange -> {
                stateChange(quizDifficulty = intent.difficulty)
            }

            is FilterQuizIntent.QuizDifficultyExpandedChange -> {
                stateChange(quizDifficultyExpanded = intent.expanded)
            }

            is FilterQuizIntent.LoadQuestions -> {
                try {
                    error("")
                } catch (_: Exception) {
                    intent.onError("Ошибка!")
                }
            }
        }
    }

    private fun stateChange(
        quizCategory: QuizCategory? = _state.value.quizCategory,
        quizDifficulty: QuizDifficulty? = _state.value.quizDifficulty,
        quizCategoryExpanded: Boolean = _state.value.quizCategoryExpanded,
        quizDifficultyExpanded: Boolean = _state.value.quizDifficultyExpanded
    ) {
        _state.value = _state.value.copy(
            quizCategory = quizCategory,
            quizDifficulty = quizDifficulty,
            quizCategoryExpanded = quizCategoryExpanded,
            quizDifficultyExpanded = quizDifficultyExpanded,
            furtherButtonEnabled = quizCategory != null && quizDifficulty != null
        )
    }
}