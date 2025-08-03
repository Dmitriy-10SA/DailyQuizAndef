package com.andef.dailyquiz.quiz.presentation.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question
import com.andef.dailyquiz.quiz.domain.usecases.LoadQuestionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel для экрана выбора фильтров квиза.
 *
 * Обрабатывает действия пользователя, изменяет состояние экрана и загружает вопросы с помощью [LoadQuestionsUseCase].
 */
class FilterQuizViewModel @Inject constructor(
    private val loadQuestionsUseCase: LoadQuestionsUseCase
) : ViewModel() {
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
                loadQuestions(onSuccess = intent.onSuccess, onError = intent.onError)
            }
        }
    }

    /**
     * Загружает вопросы и формирует список перемешанных ответов.
     *
     * @param onSuccess Колбэк при успешной загрузке.
     * @param onError Колбэк при ошибке загрузки.
     */
    private fun loadQuestions(
        onSuccess: (List<Question>, List<List<String>>, QuizCategory, QuizDifficulty) -> Unit,
        onError: (Int) -> Unit
    ) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val quizCategory = _state.value.quizCategory
                    ?: throw IllegalArgumentException("Quiz category is not selected")
                val quizDifficulty = _state.value.quizDifficulty
                    ?: throw IllegalArgumentException("Quiz difficulty is not selected")
                val questions = withContext(Dispatchers.IO) {
                    loadQuestionsUseCase.invoke(
                        category = quizCategory,
                        difficulty = quizDifficulty
                    )
                }
                val shuffledAnswers = withContext(Dispatchers.IO) {
                    mutableListOf<List<String>>().apply {
                        questions.forEach { question ->
                            add(question.incorrectAnswers.plus(question.correctAnswer).shuffled())
                        }
                    }
                }
                onSuccess(questions, shuffledAnswers, quizCategory, quizDifficulty)
            } catch (_: Exception) {
                onError(R.string.error_toast_msg)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    /**
     * Обновляет состояние [FilterQuizState] с учетом новых значений.
     */
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