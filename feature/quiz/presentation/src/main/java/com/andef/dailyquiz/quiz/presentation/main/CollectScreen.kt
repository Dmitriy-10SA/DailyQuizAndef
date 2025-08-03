package com.andef.dailyquiz.quiz.presentation.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.quiz.presentation.filter.FilterQuizScreen
import com.andef.dailyquiz.quiz.presentation.quiz.QuizScreen
import kotlinx.coroutines.launch

@Composable
fun CollectScreen(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val viewModel: CollectScreenViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
                CollectScreenStep.Filter -> {
                    FilterQuizScreen(
                        viewModelFactory = viewModelFactory,
                        onSuccessQuestionsLoad = { questions, shuffledAnswers, category, difficulty ->
                            viewModel.send(
                                CollectScreenIntent.StepChange(
                                    step = CollectScreenStep.Quiz(
                                        questions = questions,
                                        shuffledAnswers = shuffledAnswers,
                                        category = category,
                                        difficulty = difficulty,
                                    )
                                )
                            )
                        },
                        onErrorQuestionsLoad = { msgResId ->
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(context.getString(msgResId))
                            }
                        }
                    )
                }

                is CollectScreenStep.Quiz -> {
                    QuizScreen(
                        questions = step.questions,
                        shuffledAnswers = step.shuffledAnswers,
                        category = step.category,
                        difficulty = step.difficulty,
                        viewModelFactory = viewModelFactory,
                        onSuccessFinished = { correctAnsCnt, userAnswers, questions ->
                            viewModel.send(
                                CollectScreenIntent.StepChange(
                                    step = CollectScreenStep.Result(
                                        correctAnsCnt = correctAnsCnt,
                                        userAnswers = userAnswers,
                                        questions = questions
                                    )
                                )
                            )
                        },
                        onFailureAddQuiz = navHostController::popBackStack,
                        onFailureFinished = navHostController::popBackStack
                    )
                }

                is CollectScreenStep.Result -> {

                }
            }
        }
    }
}