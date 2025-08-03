package com.andef.dayliquiz.history.domain

import com.andef.dailyquiz.core.domain.repository.QuizRepository
import javax.inject.Inject

/**
 * UseCase для удаления пройденной викторины (из истории прохождения викторин)
 */
class DeleteQuizUseCase @Inject constructor(private val repository: QuizRepository) {
    suspend operator fun invoke(id: Long) = repository.deleteQuiz(id)
}