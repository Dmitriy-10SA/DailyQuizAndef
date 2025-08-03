package com.andef.dailyquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andef.dailyquiz.core.design.DailyQuizTheme
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.snackbar.ui.UiSnackbar
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.core.navigation.graph.AppNavGraph
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private val component by lazy {
        (application as DailyQuizApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            val snackbarHostState = remember { SnackbarHostState() }
            val navHostController = rememberNavController()
            val viewModelFactory = component.viewModelFactory

            settingSystemUi(systemUiController)

            MainContent(
                snackbarHostState = snackbarHostState,
                navHostController = navHostController,
                viewModelFactory = viewModelFactory
            )
        }
    }
}

@Composable
private fun MainContent(
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory
) {
    DailyQuizTheme {
        Scaffold(
            snackbarHost = {
                UiSnackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .padding(bottom = 48.dp),
                    snackbarHostState = snackbarHostState
                )
            }
        ) { paddingValues ->
            AppNavGraph(
                navHostController = navHostController,
                viewModelFactory = viewModelFactory,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState
            )
        }
    }
}

private fun settingSystemUi(systemUiController: SystemUiController) {
    with(systemUiController) {
        setNavigationBarColor(color = IndigoBlue, darkIcons = false)
        setStatusBarColor(color = IndigoBlue, darkIcons = false)
    }
}