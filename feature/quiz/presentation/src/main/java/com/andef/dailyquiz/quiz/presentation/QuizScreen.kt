package com.andef.dailyquiz.quiz.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory

@Composable
fun QuizScreen(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues
) {
    val viewModel: QuizViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    AnimatedContent(
        targetState = state.value.step
    ) { step ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (step) {
                QuizStep.Filter -> {
                    FilterQuizScreen(
                        state = state,
                        onQuizCategoryClick = { category ->
                            viewModel.send(QuizIntent.QuizCategoryChange(category))
                        },
                        onQuizDifficultyClick = { difficulty ->
                            viewModel.send(QuizIntent.QuizDifficultyChange(difficulty))
                        },
                        onQuizCategoryExpandedChange = { expanded ->
                            viewModel.send(QuizIntent.QuizCategoryExpandedChange(expanded))
                        },
                        onQuizDifficultyExpandedChange = { expanded ->
                            viewModel.send(QuizIntent.QuizDifficultyExpandedChange(expanded))
                        }
                    )
                }

                QuizStep.Quiz -> {

                }

                QuizStep.Result -> {

                }
            }
        }
    }
}