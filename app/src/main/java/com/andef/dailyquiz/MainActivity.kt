package com.andef.dailyquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.andef.dailyquiz.core.design.DailyQuizTheme
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.navigation.graph.AppNavGraph
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private val component by lazy { (application as DailyQuizApp).component }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            settingSystemUi(rememberSystemUiController())
            val navHostController = rememberNavController()
            DailyQuizTheme {
                Scaffold { paddingValues ->
                    AppNavGraph(
                        navHostController = navHostController,
                        viewModelFactory = component.viewModelFactory,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

private fun settingSystemUi(systemUiController: SystemUiController) {
    with(systemUiController) {
        setNavigationBarColor(color = IndigoBlue, darkIcons = false)
        setStatusBarColor(color = IndigoBlue, darkIcons = false)
    }
}