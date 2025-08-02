package com.andef.dailyquiz.quiz.presentation

import androidx.lifecycle.ViewModel
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class QuizViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state

    fun send(intent: QuizIntent) {
        when (intent) {
            is QuizIntent.QuizCategoryChange -> {
                filterChange(quizCategory = intent.category)
            }

            is QuizIntent.QuizCategoryExpandedChange -> {
                filterChange(quizCategoryExpanded = intent.expanded)
            }

            is QuizIntent.QuizDifficultyChange -> {
                filterChange(quizDifficulty = intent.difficulty)
            }

            is QuizIntent.QuizDifficultyExpandedChange -> {
                filterChange(quizDifficultyExpanded = intent.expanded)
            }
        }
    }

    private fun filterChange(
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