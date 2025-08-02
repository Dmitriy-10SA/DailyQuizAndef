package com.andef.dailyquiz.core.navigation.routes

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