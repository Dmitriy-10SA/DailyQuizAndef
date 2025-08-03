package com.andef.dayliquiz.history.domain

import com.andef.dailyquiz.core.domain.repository.QuizRepository
import javax.inject.Inject

/**
 * UseCase для получения всех пройденных викторин (flow)
 */
class GetQuizzesUseCase @Inject constructor(private val repository: QuizRepository) {
    operator fun invoke() = repository.getQuizzes()
}