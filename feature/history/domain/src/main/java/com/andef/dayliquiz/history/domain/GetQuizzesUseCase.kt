package com.andef.dayliquiz.history.domain

import com.andef.dailyquiz.core.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizzesUseCase @Inject constructor(private val repository: QuizRepository) {
    operator fun invoke() = repository.getQuizzes()
}