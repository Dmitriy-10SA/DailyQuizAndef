package com.andef.dailyquiz.quiz.data.repository

import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.data.api.QuestionApiService
import com.andef.dailyquiz.quiz.data.mapper.QuestionMapper
import com.andef.dailyquiz.quiz.domain.entities.Question
import com.andef.dailyquiz.quiz.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val api: QuestionApiService,
    private val mapper: QuestionMapper
) : QuestionRepository {
    override suspend fun loadQuestions(
        amount: Int,
        category: QuizCategory,
        difficulty: QuizDifficulty
    ): List<Question> {
        return api.loadQuestions(amount, category.apiId, difficulty.apiTitle).results.map {
            mapper.toQuestion(it)
        }
    }
}