package com.andef.dailyquiz.quiz.presentation.main

import android.content.Context
import androidx.activity.compose.BackHandler
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
import com.andef.dailyquiz.quiz.presentation.result.ResultScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Главный экран для викторины, который управляет переходами между этапами:
 * - фильтрация параметров;
 * - прохождение викторины;
 * - отображение результата.
 */
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
        when (step) {
            CollectScreenStep.Filter -> {
                Filter(
                    paddingValues = paddingValues,
                    viewModel = viewModel,
                    viewModelFactory = viewModelFactory,
                    navHostController = navHostController,
                    snackbarHostState = snackbarHostState,
                    scope = scope,
                    context = context
                )
            }

            is CollectScreenStep.Quiz -> {
                Quiz(
                    paddingValues = paddingValues,
                    step = step,
                    viewModel = viewModel,
                    viewModelFactory = viewModelFactory,
                    navHostController = navHostController
                )
            }

            is CollectScreenStep.Result -> {
                Result(step = step, navHostController = navHostController)
            }
        }
    }
}

/**
 * Экран фильтрации параметров викторины (категория, сложность).
 *
 * Загружает вопросы при подтверждении параметров. В случае успеха — переходит к этапу Quiz,
 * в случае ошибки — показывает Snackbar.
 */
@Composable
private fun Filter(
    paddingValues: PaddingValues,
    viewModel: CollectScreenViewModel,
    viewModelFactory: ViewModelFactory,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            },
            onBack = navHostController::popBackStack
        )
        BackHandler(onBack = navHostController::popBackStack)
    }
}

/**
 * Экран прохождения викторины. Позволяет пользователю ответить на вопросы.
 *
 * При завершении викторины отображает экран результата или возвращается назад в случае ошибки.
 */
@Composable
private fun Quiz(
    paddingValues: PaddingValues,
    step: CollectScreenStep.Quiz,
    viewModel: CollectScreenViewModel,
    viewModelFactory: ViewModelFactory,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            questions = questions,
                            shuffledAnswers = step.shuffledAnswers
                        )
                    )
                )
            },
            onFailureAddQuiz = navHostController::popBackStack,
            onFailureFinished = navHostController::popBackStack
        )
        BackHandler {}
    }
}

/**
 * Экран результата викторины. Показывает правильные/неправильные ответы.
 */
@Composable
private fun Result(step: CollectScreenStep.Result, navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResultScreen(
            correctAnsCnt = step.correctAnsCnt,
            userAnswers = step.userAnswers,
            shuffledAnswers = step.shuffledAnswers,
            questions = step.questions,
            onRetryClick = navHostController::popBackStack
        )
        BackHandler(onBack = navHostController::popBackStack)
    }
}