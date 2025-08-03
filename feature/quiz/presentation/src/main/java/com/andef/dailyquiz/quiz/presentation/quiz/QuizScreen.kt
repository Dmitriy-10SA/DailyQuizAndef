package com.andef.dailyquiz.quiz.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.SoftLilac
import com.andef.dailyquiz.core.design.answer.option.type.UiAnswerOptionType
import com.andef.dailyquiz.core.design.answer.option.ui.UiAnswerOption
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.dialog.type.UiDialogType
import com.andef.dailyquiz.core.design.dialog.ui.UiDialog
import com.andef.dailyquiz.core.design.loading.ui.UiLoading
import com.andef.dailyquiz.core.design.timer.ui.UiTimer
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.quiz.domain.entities.Question

/**
 * Основной UI-компонент для экрана прохождения викторины.
 *
 * Управляет отображением прогресса, обработкой ответов, переходом к следующему вопросу
 * и завершением викторины. Инициализирует вопросы и таймер, отображает загрузку, диалоги ошибок.
 */
@Composable
fun ColumnScope.QuizScreen(
    questions: List<Question>,
    category: QuizCategory,
    difficulty: QuizDifficulty,
    onSuccessFinished: (Int, Map<Int, String>, List<Question>) -> Unit,
    onFailureAddQuiz: () -> Unit,
    onFailureFinished: () -> Unit,
    shuffledAnswers: List<List<String>>,
    viewModelFactory: ViewModelFactory
) {
    val viewModel: QuizScreenViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    InitEffect(
        viewModel = viewModel,
        questions = questions,
        shuffledAnswers = shuffledAnswers,
        category = category,
        difficulty = difficulty,
        onSuccessFinished = onSuccessFinished
    )

    when (state.value.isLoading) {
        true -> UiLoading()
        false -> {
            MainContent(
                state = state,
                onAnswerClick = { answer ->
                    viewModel.send(QuizScreenIntent.AnswerChoose(answer))
                },
                onNextClick = { viewModel.send(QuizScreenIntent.NextClick) }
            )
        }
    }
    Dialogs(
        viewModel = viewModel,
        state = state,
        onFailureAddQuiz = onFailureAddQuiz,
        onFailureFinished = onFailureFinished
    )
}

/**
 * Эффект инициализации, запускаемый один раз при появлении экрана.
 *
 * Отправляет интент с инициализацией вопросов и таймера, регистрирует логику завершения викторины.
 *
 * В случае успешного завершения — сохраняет результат. В случае ошибки — отображает диалог.
 */
@Composable
private fun InitEffect(
    viewModel: QuizScreenViewModel,
    questions: List<Question>,
    shuffledAnswers: List<List<String>>,
    category: QuizCategory,
    difficulty: QuizDifficulty,
    onSuccessFinished: (Int, Map<Int, String>, List<Question>) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.send(
            QuizScreenIntent.InitQuestionsAndTimer(
                questions = questions,
                shuffledAnswers = shuffledAnswers,
                category = category,
                difficulty = difficulty,
                onQuizFinished = { success ->
                    when (success) {
                        true -> {
                            viewModel.send(
                                QuizScreenIntent.SaveResults(
                                    onSuccess = onSuccessFinished,
                                    onError = {
                                        viewModel.send(
                                            QuizScreenIntent.ChangeErrorDialogVisible(true)
                                        )
                                    }
                                )
                            )
                        }

                        false -> {
                            viewModel.send(QuizScreenIntent.ChangeTimeOverDialogVisible(true))
                        }
                    }
                }
            )
        )
    }
}

/**
 * Компонент, отображающий диалоги при различных ошибках во время прохождения викторины.
 *
 * Содержит:
 * - диалог при истечении времени;
 * - диалог при ошибке сохранения результата.
 */
@Composable
private fun Dialogs(
    viewModel: QuizScreenViewModel,
    state: State<QuizScreenState>,
    onFailureFinished: () -> Unit,
    onFailureAddQuiz: () -> Unit
) {
    UiDialog(
        type = UiDialogType.WithActionButton(
            buttonText = stringResource(R.string.retry),
            onClick = {
                viewModel.send(QuizScreenIntent.ChangeTimeOverDialogVisible(false))
                onFailureFinished()
            }
        ),
        onDismissRequest = {
            viewModel.send(QuizScreenIntent.ChangeTimeOverDialogVisible(false))
            onFailureFinished()
        },
        isVisible = state.value.timeOverDialogVisible,
        title = stringResource(R.string.time_over),
        subTitle = stringResource(R.string.time_over_description),
    )
    UiDialog(
        type = UiDialogType.WithDismissButton,
        isVisible = state.value.errorDialogVisible,
        title = stringResource(R.string.oops_error),
        subTitle = stringResource(R.string.save_quiz_error),
        onDismissRequest = {
            viewModel.send(QuizScreenIntent.ChangeErrorDialogVisible(false))
            onFailureAddQuiz()
        }
    )
}

/**
 * Основной контент викторины, включающий:
 * - логотип;
 * - карточку с вопросом;
 * - варианты ответов;
 * - кнопку "Далее".
 *
 * Показывается после завершения загрузки.
 */
@Composable
private fun ColumnScope.MainContent(
    state: State<QuizScreenState>,
    onAnswerClick: (String) -> Unit,
    onNextClick: () -> Unit
) {
    if (state.value.questions.isNotEmpty()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 41.dp)
                .padding(horizontal = 90.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.daily_quiz_logo)
        )
        QuestionCard(state = state, onAnswerClick = onAnswerClick, onNextClick = onNextClick)
    }
}

/**
 * Карточка текущего вопроса.
 *
 * Содержит:
 * - таймер;
 * - порядковый номер и сам вопрос;
 * - список ответов с визуальной подсветкой;
 * - кнопку перехода к следующему вопросу.
 */
@Composable
private fun ColumnScope.QuestionCard(
    state: State<QuizScreenState>,
    onAnswerClick: (String) -> Unit,
    onNextClick: () -> Unit
) {
    val currentQuestionIndex = state.value.currentQuestionIndex
    val currentQuestion = state.value.questions[currentQuestionIndex]
    UiCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            UiTimer(
                modifier = Modifier.fillMaxWidth(),
                currentTimeSeconds = state.value.currentTimeSeconds,
                totalTimeSeconds = QuizScreenState.TOTAL_TIME_SECONDS
            )
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                QuestionCardTitleAndSubtitle(
                    currentQuestionIndex = currentQuestionIndex,
                    currentQuestion = currentQuestion,
                    questionsSize = state.value.questions.size
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    state.value.shuffledAnswers[currentQuestionIndex].forEach { answer ->
                        UiAnswerOption(
                            text = answer,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !state.value.showRightAnswer,
                            type = getUiAnswerOptionType(state = state, answer = answer),
                            onClick = { onAnswerClick(answer) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            UiButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNextClick,
                text = stringResource(R.string.further),
                enabled = state.value.userAnswers[state.value.currentQuestionIndex] != null &&
                        !state.value.showRightAnswer
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/**
 * Заголовок карточки вопроса, включающий:
 * - номер текущего вопроса;
 * - общее количество;
 * - сам текст вопроса.
 */
@Composable
private fun ColumnScope.QuestionCardTitleAndSubtitle(
    currentQuestionIndex: Int,
    currentQuestion: Question,
    questionsSize: Int
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "${stringResource(R.string.question)} " +
                "${currentQuestionIndex + 1} " +
                "${stringResource(R.string.from)} $questionsSize",
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = 0.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center,
        color = SoftLilac
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = currentQuestion.text,
        fontSize = 18.sp,
        fontWeight = FontWeight.W600,
        letterSpacing = 0.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Center,
        color = Black
    )
}

/**
 * Определяет тип визуальной подсветки ответа (правильный, неправильный, выбранный и т.д.)
 * в зависимости от состояния викторины.
 *
 * @param state Состояние викторины.
 * @param answer Вариант ответа, для которого определяется тип.
 *
 * @return Тип визуального отображения ответа.
 */
private fun getUiAnswerOptionType(
    state: State<QuizScreenState>,
    answer: String
): UiAnswerOptionType {
    val showRightAnswer = state.value.showRightAnswer
    val currentIndex = state.value.currentQuestionIndex
    val userAnswer = state.value.userAnswers[currentIndex]
    val correctAnswer = state.value.questions[currentIndex].correctAnswer

    return when {
        showRightAnswer && answer == userAnswer && answer == correctAnswer -> UiAnswerOptionType.Right
        showRightAnswer && answer == userAnswer && answer != correctAnswer -> UiAnswerOptionType.Wrong
        !showRightAnswer && answer == userAnswer -> UiAnswerOptionType.Selected
        else -> UiAnswerOptionType.Default
    }
}