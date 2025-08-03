package com.andef.dailyquiz.quiz.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.dailyquiz.core.domain.entites.Quiz
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question
import com.andef.dailyquiz.quiz.domain.usecases.AddQuizUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

/**
 * ViewModel для экрана викторины. Управляет логикой прохождения, таймером и сохранением результатов.
 *
 * @param addQuizUseCase UseCase для добавления (сохранения) результата викторины.
 */
class QuizScreenViewModel @Inject constructor(
    private val addQuizUseCase: AddQuizUseCase
) : ViewModel() {
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
                    onQuizFinished = intent.onQuizFinished,
                    category = intent.category,
                    difficulty = intent.difficulty
                )
            }

            is QuizScreenIntent.ChangeErrorDialogVisible -> {
                _state.value = _state.value.copy(errorDialogVisible = intent.isVisible)
            }

            is QuizScreenIntent.ChangeTimeOverDialogVisible -> {
                _state.value = _state.value.copy(timeOverDialogVisible = intent.isVisible)
            }

            is QuizScreenIntent.SaveResults -> {
                saveResults(onSuccess = intent.onSuccess, onError = intent.onError)
            }
        }
    }

    /**
     * Подсчитывает количество правильных ответов и сохраняет результат викторины через use-case.
     *
     * @param onSuccess Колбэк при успешном сохранении.
     * @param onError Колбэк при ошибке сохранения.
     */
    private fun saveResults(
        onSuccess: (Int, Map<Int, String>, List<Question>) -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val userAnswers = _state.value.userAnswers
                val questions = _state.value.questions
                val category = _state.value.category
                    ?: throw IllegalArgumentException("Category is null")
                val difficulty = _state.value.difficulty
                    ?: throw IllegalArgumentException("Difficulty is null")
                val correctAnswersCnt = withContext(Dispatchers.IO) {
                    var cnt = 0
                    questions.forEachIndexed { index, question ->
                        if (userAnswers[index] == question.correctAnswer) cnt++
                    }
                    addQuizUseCase.invoke(
                        Quiz(
                            id = 0,
                            correctAnswersCnt = cnt,
                            date = LocalDate.now(),
                            time = LocalTime.now(),
                            category = category,
                            difficulty = difficulty
                        )
                    )
                    cnt
                }
                onSuccess(correctAnswersCnt, userAnswers, questions)
            } catch (_: Exception) {
                onError()
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    /**
     * Сохраняет ответ пользователя на текущий вопрос.
     *
     * @param questionIndex Индекс текущего вопроса.
     * @param answer Текст выбранного ответа.
     */
    private fun answerChoose(questionIndex: Int, answer: String) {
        viewModelScope.launch {
            val userAnswers = _state.value.userAnswers.toMutableMap().apply {
                this[questionIndex] = answer
            }
            _state.value = _state.value.copy(userAnswers = userAnswers)
        }
    }

    /**
     * Логика перехода к следующему вопросу. Показывает правильный ответ, делает паузу 2 секунды.
     * При последнем вопросе завершает викторину.
     *
     * @param questionIndex Индекс текущего вопроса.
     */
    private fun nextClick(questionIndex: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(showRightAnswer = true)
            if (questionIndex < _state.value.questions.size - 1) {
                delay(2000)
                _state.value = _state.value.copy(
                    showRightAnswer = false,
                    currentQuestionIndex = questionIndex + 1
                )
            } else {
                _state.value = _state.value.copy(quizFinished = true)
            }
        }
    }

    /**
     * Инициализация вопросов, ответов, категории и запуска таймера.
     * Выполняется один раз при первом старте.
     *
     * @param questions Список вопросов.
     * @param category Категория викторины.
     * @param difficulty Сложность викторины.
     * @param shuffledAnswers Перемешанные ответы.
     * @param onQuizFinished Колбэк завершения викторины (по таймеру или по кнопке).
     */
    private var isFirstStart = true
    private fun initQuestions(
        questions: List<Question>,
        category: QuizCategory,
        difficulty: QuizDifficulty,
        shuffledAnswers: List<List<String>>,
        onQuizFinished: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            if (isFirstStart) {
                isFirstStart = false
                _state.value = _state.value.copy(
                    questions = questions,
                    category = category,
                    difficulty = difficulty,
                    shuffledAnswers = shuffledAnswers
                )
                startTimer(onQuizFinished = onQuizFinished)
            }
        }
    }

    /**
     * Запускает таймер с интервалом 1 секунда.
     * По завершению времени или викторины вызывает onQuizFinished.
     *
     * @param onQuizFinished Колбэк завершения викторины.
     */
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
                delay(2000)
                onQuizFinished(true)
            } else {
                onQuizFinished(false)
            }
        }
    }
}