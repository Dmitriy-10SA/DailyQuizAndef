package com.andef.dailyquiz.quiz.data.mapper

import com.andef.dailyquiz.quiz.data.dto.QuestionDto
import com.andef.dailyquiz.quiz.domain.entities.Question
import javax.inject.Inject

class QuestionMapper @Inject constructor() {
    fun toQuestion(questionDto: QuestionDto) = Question(
        text = questionDto.text,
        correctAnswer = questionDto.correctAnswer,
        incorrectAnswers = questionDto.incorrectAnswers
    )
}