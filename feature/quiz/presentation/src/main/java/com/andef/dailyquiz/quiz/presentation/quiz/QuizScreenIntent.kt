package com.andef.dailyquiz.quiz.presentation.quiz

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

/**
 * Намерения (интенты) экрана викторины.
 */
sealed class QuizScreenIntent {
    /**
     * Инициализация вопросов и запуск таймера.
     *
     * @param questions Список вопросов.
     * @param category Категория викторины.
     * @param difficulty Уровень сложности.
     * @param shuffledAnswers Перемешанные варианты ответов.
     * @param onQuizFinished Колбэк по завершению викторины (true — по кнопке, false — по таймеру).
     */
    data class InitQuestionsAndTimer(
        val questions: List<Question>,
        val category: QuizCategory,
        val difficulty: QuizDifficulty,
        val shuffledAnswers: List<List<String>>,
        val onQuizFinished: (Boolean) -> Unit
    ) : QuizScreenIntent()

    /**
     * Выбор пользователем ответа.
     *
     * @param answer Текст выбранного ответа.
     */
    data class AnswerChoose(val answer: String) : QuizScreenIntent()

    /** Переход к следующему вопросу. */
    data object NextClick : QuizScreenIntent()

    /**
     * Сохранение результатов викторины.
     *
     * @param onSuccess Колбэк при успешном сохранении: передаёт количество правильных ответов, ответы пользователя, список вопросов.
     * @param onError Колбэк при ошибке сохранения.
     */
    data class SaveResults(
        val onSuccess: (Int, Map<Int, String>, List<Question>) -> Unit,
        val onError: () -> Unit
    ) : QuizScreenIntent()

    /**
     * Изменение видимости диалога ошибки.
     *
     * @param isVisible Флаг видимости.
     */
    data class ChangeErrorDialogVisible(val isVisible: Boolean) : QuizScreenIntent()

    /**
     * Изменение видимости диалога "время вышло".
     *
     * @param isVisible Флаг видимости.
     */
    data class ChangeTimeOverDialogVisible(val isVisible: Boolean) : QuizScreenIntent()
}