package com.andef.dailyquiz.quiz.domain.usecases

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.repository.QuestionRepository
import javax.inject.Inject

class LoadQuestionsUseCase @Inject constructor(private val repository: QuestionRepository) {
    suspend operator fun invoke(
        amount: Int = 5,
        category: QuizCategory,
        difficulty: QuizDifficulty
    ) = repository.loadQuestions(amount, category, difficulty)
}