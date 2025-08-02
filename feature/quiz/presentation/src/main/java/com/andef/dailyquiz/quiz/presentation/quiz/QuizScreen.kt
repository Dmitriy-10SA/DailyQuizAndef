package com.andef.dailyquiz.quiz.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.andef.dailyquiz.core.design.loading.ui.UiLoading
import com.andef.dailyquiz.core.design.timer.ui.UiTimer
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.quiz.domain.entities.Question

@Composable
fun ColumnScope.QuizScreen(
    questions: List<Question>,
    shuffledAnswers: List<List<String>>,
    viewModelFactory: ViewModelFactory
) {
    val viewModel: QuizScreenViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    InitEffect(viewModel = viewModel, questions = questions, shuffledAnswers = shuffledAnswers)

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
}

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
        UiTimer(
            modifier = Modifier.fillMaxWidth(),
            currentTimeSeconds = state.value.currentTimeSeconds,
            totalTimeSeconds = QuizScreenState.TOTAL_TIME_SECONDS
        )
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
        UiButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextClick,
            text = stringResource(R.string.further),
            enabled = state.value.userAnswers[state.value.currentQuestionIndex] != null &&
                    !state.value.showRightAnswer
        )
    }
}

@Composable
private fun QuestionCardTitleAndSubtitle(
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

@Composable
private fun InitEffect(
    viewModel: QuizScreenViewModel,
    shuffledAnswers: List<List<String>>,
    questions: List<Question>
) {
    LaunchedEffect(Unit) {
        viewModel.send(
            QuizScreenIntent.InitQuestionsAndTimer(
                questions = questions,
                shuffledAnswers = shuffledAnswers,
                onQuizFinished = { success ->
                    when (success) {
                        true -> TODO()
                        false -> TODO("Показываем время вышло. Бла-бла-бла")
                    }
                }
            )
        )
    }
}