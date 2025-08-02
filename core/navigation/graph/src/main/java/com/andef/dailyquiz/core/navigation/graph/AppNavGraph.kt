package com.andef.dailyquiz.core.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.core.navigation.routes.Screen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Start.route
    ) {
        composable(route = Screen.Start.route) {

        }
        composable(route = Screen.Quiz.route) {

        }
        composable(route = Screen.History.route) {

        }
    }
}