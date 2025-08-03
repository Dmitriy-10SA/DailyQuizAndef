package com.andef.dailyquiz.quiz.domain.usecases

import com.andef.dailyquiz.core.domain.entites.Quiz
import com.andef.dailyquiz.core.domain.repository.QuizRepository
import javax.inject.Inject

/**
 * UseCase для добавления новой викторины в хранилище.
 *
 * Используется для сохранения завершённой викторины в локальную базу данных.
 *
 * @property repository Репозиторий для работы с викторинами.
 */
class AddQuizUseCase @Inject constructor(private val repository: QuizRepository) {
    suspend operator fun invoke(quiz: Quiz) = repository.addQuiz(quiz)
}