package com.andef.dailyquiz.quiz.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.dailyquiz.quiz.domain.entities.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuizScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(QuizScreenState())
    val state: StateFlow<QuizScreenState> = _state

    fun send(intent: QuizScreenIntent) {
        when (intent) {
            is QuizScreenIntent.AnswerChoose -> {
                answerChoose(
                    questionIndex = _state.value.currentQuestionIndex,
                    answer = intent.answer
                )
            }

            is QuizScreenIntent.NextClick -> {
                nextClick(questionIndex = _state.value.currentQuestionIndex)
            }

            is QuizScreenIntent.InitQuestionsAndTimer -> {
                initQuestions(
                    questions = intent.questions,
                    shuffledAnswers = intent.shuffledAnswers,
                    onQuizFinished = intent.onQuizFinished
                )
            }
        }
    }

    private fun answerChoose(questionIndex: Int, answer: String) {
        viewModelScope.launch {
            val userAnswers = _state.value.userAnswers.toMutableMap().apply {
                this[questionIndex] = answer
            }
            _state.value = _state.value.copy(userAnswers = userAnswers)
        }
    }

    private fun nextClick(questionIndex: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(showRightAnswer = true)
            delay(2000)
            if (questionIndex < _state.value.questions.size) {
                _state.value = _state.value.copy(
                    showRightAnswer = false,
                    currentQuestionIndex = questionIndex + 1
                )
            } else {
                _state.value = _state.value.copy(quizFinished = true)
            }
        }
    }

    private var isFirstStart = true
    private fun initQuestions(
        questions: List<Question>,
        shuffledAnswers: List<List<String>>,
        onQuizFinished: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            if (isFirstStart) {
                isFirstStart = false
                _state.value = _state.value.copy(
                    questions = questions,
                    shuffledAnswers = shuffledAnswers
                )
                startTimer(onQuizFinished = onQuizFinished)
            }
        }
    }

    private var timerJob: Job? = null
    private fun startTimer(onQuizFinished: (Boolean) -> Unit) {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var seconds = 0
            while (seconds <= QuizScreenState.TOTAL_TIME_SECONDS && !_state.value.quizFinished) {
                _state.value = _state.value.copy(currentTimeSeconds = seconds)
                delay(1000)
                seconds++
            }
            if (_state.value.quizFinished) {
                onQuizFinished(true)
            } else {
                onQuizFinished(false)
            }
        }
    }
}