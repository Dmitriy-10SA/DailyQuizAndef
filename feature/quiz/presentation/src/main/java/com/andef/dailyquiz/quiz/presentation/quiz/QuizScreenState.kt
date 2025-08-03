package com.andef.dailyquiz.quiz.presentation.quiz

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

/**
 * Состояние экрана викторины. Используется для управления UI.
 *
 * @property currentQuestionIndex Индекс текущего вопроса.
 * @property questions Список всех вопросов викторины.
 * @property shuffledAnswers Перемешанные ответы для каждого вопроса.
 * @property showRightAnswer Показывать ли правильный ответ.
 * @property userAnswers Ответы, выбранные пользователем (по индексу вопроса).
 * @property category Категория викторины.
 * @property difficulty Уровень сложности.
 * @property currentTimeSeconds Текущее время прохождения викторины (в секундах).
 * @property quizFinished Признак завершения викторины.
 * @property nextButtonEnabled Кнопка "Далее" доступна.
 * @property errorDialogVisible Видимость диалога ошибки сохранения.
 * @property timeOverDialogVisible Видимость диалога "время вышло".
 * @property isLoading Показывается ли индикатор загрузки.
 */
data class QuizScreenState(
    val currentQuestionIndex: Int = 0,
    val questions: List<Question> = listOf<Question>(),
    val shuffledAnswers: List<List<String>> = emptyList<List<String>>(),
    val showRightAnswer: Boolean = false,
    val userAnswers: Map<Int, String> = mapOf<Int, String>(),
    val category: QuizCategory? = null,
    val difficulty: QuizDifficulty? = null,
    val currentTimeSeconds: Int = 0,
    val quizFinished: Boolean = false,
    val nextButtonEnabled: Boolean = false,
    val errorDialogVisible: Boolean = false,
    val timeOverDialogVisible: Boolean = false,
    val isLoading: Boolean = false
) {
    companion object {
        /** Общее доступное время на прохождение викторины (в секундах). */
        const val TOTAL_TIME_SECONDS = 300
    }
}
