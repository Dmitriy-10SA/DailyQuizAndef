package com.andef.dailyquiz.history.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.dialog.type.UiDialogType
import com.andef.dailyquiz.core.design.dialog.ui.UiDialog
import com.andef.dailyquiz.core.design.icon.button.ui.UiIconButton
import com.andef.dailyquiz.core.design.loading.ui.UiLoading
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory
import com.andef.dailyquiz.core.domain.entites.Quiz

@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    val viewModel: HistoryScreenViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    val selectedQuiz = remember { mutableStateOf<Quiz?>(null) }

    when {
        state.value.isLoading -> UiLoading()
        state.value.quizzes.isEmpty() && !state.value.isError -> EmptyQuizzesContent(
            paddingValues = paddingValues,
            onButtonClick = navHostController::popBackStack,
            navHostController = navHostController,
            selectedQuiz = selectedQuiz
        )

        else -> MainContent(
            navHostController = navHostController,
            selectedQuiz = selectedQuiz,
            state = state
        )
    }
    UiDialog(
        type = UiDialogType.WithActionButton(
            buttonText = stringResource(com.andef.dailyquiz.core.design.R.string.retry_error_button),
            onClick = { viewModel.send(HistoryScreenIntent.SubscribeForQuizzes) }
        ),
        isVisible = state.value.isError,
        title = stringResource(com.andef.dailyquiz.core.design.R.string.oops_error),
        subTitle = stringResource(com.andef.dailyquiz.core.design.R.string.error_load_quiz),
        onDismissRequest = navHostController::popBackStack
    )
}

@Composable
private fun MainContent(
    navHostController: NavHostController,
    state: State<HistoryScreenState>,
    selectedQuiz: MutableState<Quiz?>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(if (selectedQuiz.value != null) IndigoBlue.copy(alpha = 0.5f) else IndigoBlue)
    ) {
        item { Spacer(modifier = Modifier.statusBarsPadding()) }
        item { Header(navHostController = navHostController, selectedQuiz = selectedQuiz) }
        items(items = state.value.quizzes, key = { it.id }) { quiz ->
            Spacer(modifier = Modifier.padding(16.dp))
            HistoryItemCard(
                quizId = quiz.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .alpha(if (selectedQuiz.value != null && selectedQuiz.value != quiz) 0.5f else 1f),
                correctAnsCnt = quiz.correctAnswersCnt,
                date = quiz.date,
                time = quiz.time,
                onDismiss = { selectedQuiz.value = null },
                onLongClick = { selectedQuiz.value = quiz },
                onDeleteClick = {
                    TODO()
                }
            )
        }
        item { Spacer(modifier = Modifier.padding(16.dp)) }
        item { Spacer(modifier = Modifier.navigationBarsPadding()) }
    }
}

@Composable
private fun Header(navHostController: NavHostController, selectedQuiz: MutableState<Quiz?>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (selectedQuiz.value != null) 0.5f else 1f)
            .padding(horizontal = 20.dp)
            .padding(top = 38.dp)
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UiIconButton(
            modifier = Modifier.size(36.dp),
            icon = painterResource(com.andef.dailyquiz.core.design.R.drawable.arrow_back),
            contentDescription = stringResource(com.andef.dailyquiz.core.design.R.string.back),
            onClick = navHostController::popBackStack
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 36.dp),
            text = stringResource(com.andef.dailyquiz.core.design.R.string.history),
            fontWeight = FontWeight.W900,
            color = White,
            fontFamily = FontFamily(Font(com.andef.dailyquiz.core.design.R.font.inter)),
            letterSpacing = 0.sp,
            lineHeight = 32.sp,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun EmptyQuizzesContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    onButtonClick: () -> Unit,
    selectedQuiz: MutableState<Quiz?>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .navigationBarsPadding()
    ) {
        Header(navHostController = navHostController, selectedQuiz = selectedQuiz)
        UiCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(com.andef.dailyquiz.core.design.R.string.empty_history_card_text),
                    fontWeight = FontWeight.W400,
                    color = Black,
                    fontFamily = FontFamily(Font(com.andef.dailyquiz.core.design.R.font.inter)),
                    letterSpacing = 0.sp,
                    lineHeight = 20.sp,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                UiButton(
                    onClick = onButtonClick,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(com.andef.dailyquiz.core.design.R.string.start_quiz)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 65.dp)
                .padding(horizontal = 90.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(com.andef.dailyquiz.core.design.R.drawable.logo),
            contentDescription = stringResource(com.andef.dailyquiz.core.design.R.string.daily_quiz_logo)
        )
    }
}