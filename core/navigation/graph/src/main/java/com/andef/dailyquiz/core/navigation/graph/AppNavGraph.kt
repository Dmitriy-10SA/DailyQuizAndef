package com.andef.dailyquiz.core.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.core.navigation.routes.Screen
import com.andef.dailyquiz.quiz.presentation.main.CollectScreen
import com.andef.dailyquiz.start.presentation.StartScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Start.route
    ) {
        composable(route = Screen.Start.route) {
            StartScreen(navHostController = navHostController, paddingValues = paddingValues)
        }
        composable(route = Screen.Quiz.route) {
            CollectScreen(
                navHostController = navHostController,
                viewModelFactory = viewModelFactory,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState
            )
        }
        composable(route = Screen.History.route) {

        }
    }
}