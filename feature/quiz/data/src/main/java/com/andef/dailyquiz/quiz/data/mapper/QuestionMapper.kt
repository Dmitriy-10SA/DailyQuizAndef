package com.andef.dailyquiz.quiz.data.mapper

import com.andef.dailyquiz.quiz.data.dto.QuestionDto
import com.andef.dailyquiz.quiz.domain.entities.Question
import javax.inject.Inject

/**
 * Маппер для преобразования DTO-модели [QuestionDto] в доменную модель [Question].
 */
class QuestionMapper @Inject constructor() {
    /**
     * Преобразование [QuestionDto] в [Question].
     *
     * @param questionDto DTO вопроса.
     * @return Доменная модель вопроса.
     */
    fun toQuestion(questionDto: QuestionDto) = Question(
        text = questionDto.text,
        correctAnswer = questionDto.correctAnswer,
        incorrectAnswers = questionDto.incorrectAnswers
    )
}