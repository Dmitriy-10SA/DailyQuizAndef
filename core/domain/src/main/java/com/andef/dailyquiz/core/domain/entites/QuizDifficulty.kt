package com.andef.dailyquiz.core.domain.entites

/**
 * Сложность викторины
 *
 * @property apiTitle строка для отправки запроса к API (указываем сложность так)
 */
enum class QuizDifficulty(val apiTitle: String) {
    EASY(apiTitle = "easy"),
    MEDIUM(apiTitle = "medium"),
    HARD(apiTitle = "hard")
}