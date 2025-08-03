package com.andef.dailyquiz.history.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.icon.button.ui.UiIconButton
import com.andef.dailyquiz.core.design.loading.ui.UiLoading
import com.andef.dailyquiz.core.di.viewmodel.ViewModelFactory

@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    val viewModel: HistoryScreenViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    when (state.value.isLoading) {
        true -> UiLoading()
        false -> {
            Column(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 38.dp)
                        .padding(bottom = 40.dp),
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
                if (state.value.quizzes.isEmpty()) {
                    EmptyQuizzesContent(onButtonClick = navHostController::popBackStack)
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(items = state.value.quizzes, key = { it.id }) { quiz ->
                            HistoryItemCard(
                                quizId = quiz.id,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                correctAnsCnt = quiz.correctAnswersCnt,
                                date = quiz.date,
                                time = quiz.time
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.EmptyQuizzesContent(onButtonClick: () -> Unit) {
    UiCard(
        modifier = Modifier
            .fillMaxWidth()
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
            .padding(bottom = 64.dp)
            .padding(horizontal = 90.dp),
        contentScale = ContentScale.FillWidth,
        painter = painterResource(com.andef.dailyquiz.core.design.R.drawable.logo),
        contentDescription = stringResource(com.andef.dailyquiz.core.design.R.string.daily_quiz_logo)
    )
}