package com.andef.dailyquiz.core.navigation.routes

/**
 * Route (маршруты) для экранов графа навигации приложения
 *
 * Имеет маршруты:
 * - Start (стартовый экран)
 * - Quiz (экран, в котором происходит выбор фильтров для викторины, далее викторина и результат прохождения)
 * - History (экран со всеми пройдеными викторинами)
 */
sealed class Screen(val route: String) {
    data object Start : Screen(START_SCREEN_ROUTE)
    data object History : Screen(HISTORY_SCREEN_ROUTE)
    data object Quiz : Screen(QUIZ_SCREEN_ROUTE)

    companion object {
        private const val START_SCREEN_ROUTE = "start-screen-route"
        private const val HISTORY_SCREEN_ROUTE = "history-screen-route"
        private const val QUIZ_SCREEN_ROUTE = "quiz-screen-route"
    }
}