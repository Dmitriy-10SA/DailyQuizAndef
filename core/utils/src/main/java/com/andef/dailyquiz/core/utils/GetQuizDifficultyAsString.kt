package com.andef.dailyquiz.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty

@Composable
fun getQuizDifficultyAsString(difficulty: QuizDifficulty) = when (difficulty) {
    QuizDifficulty.EASY -> stringResource(com.andef.dailyquiz.core.design.R.string.difficulty_easy)
    QuizDifficulty.MEDIUM -> stringResource(com.andef.dailyquiz.core.design.R.string.difficulty_medium)
    QuizDifficulty.HARD -> stringResource(com.andef.dailyquiz.core.design.R.string.difficulty_hard)
}