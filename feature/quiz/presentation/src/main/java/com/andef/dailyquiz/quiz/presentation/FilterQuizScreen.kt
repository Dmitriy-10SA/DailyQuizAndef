package com.andef.dailyquiz.quiz.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.menu.ui.UiMenu
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.core.utils.getQuizCategoryAsString
import com.andef.dailyquiz.core.utils.getQuizDifficultyAsString

@Composable
fun ColumnScope.FilterQuizScreen(
    state: State<QuizState>,
    onQuizCategoryClick: (QuizCategory) -> Unit,
    onQuizCategoryExpandedChange: (Boolean) -> Unit,
    onQuizDifficultyClick: (QuizDifficulty) -> Unit,
    onQuizDifficultyExpandedChange: (Boolean) -> Unit
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 41.dp)
            .padding(horizontal = 90.dp),
        contentScale = ContentScale.FillWidth,
        painter = painterResource(com.andef.dailyquiz.core.design.R.drawable.logo),
        contentDescription = stringResource(com.andef.dailyquiz.core.design.R.string.daily_quiz_logo)
    )
    UiCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 52.dp)
            .padding(horizontal = 20.dp)
    ) {
        TitleAndSubtitleForUiCard()
        FilterMenus(
            state = state,
            onQuizCategoryClick = onQuizCategoryClick,
            onQuizCategoryExpandedChange = onQuizCategoryExpandedChange,
            onQuizDifficultyClick = onQuizDifficultyClick,
            onQuizDifficultyExpandedChange = onQuizDifficultyExpandedChange
        )
        UiButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                TODO()
            },
            text = stringResource(com.andef.dailyquiz.core.design.R.string.further),
            enabled = state.value.furtherButtonEnabled
        )
    }
}

@Composable
private fun FilterMenus(
    state: State<QuizState>,
    onQuizCategoryClick: (QuizCategory) -> Unit,
    onQuizCategoryExpandedChange: (Boolean) -> Unit,
    onQuizDifficultyClick: (QuizDifficulty) -> Unit,
    onQuizDifficultyExpandedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UiMenu(
            modifier = Modifier.fillMaxWidth(),
            items = QuizCategory.entries.toList(),
            itemToString = { category -> getQuizCategoryAsString(category) },
            value = state.value.quizCategory?.let { getQuizCategoryAsString(it) } ?: "",
            placeholderText = stringResource(com.andef.dailyquiz.core.design.R.string.category),
            onItemClick = { category ->
                onQuizCategoryExpandedChange(false)
                onQuizCategoryClick(category)
            },
            expanded = state.value.quizCategoryExpanded,
            onExpandedChange = onQuizCategoryExpandedChange
        )
        UiMenu(
            modifier = Modifier.fillMaxWidth(),
            items = QuizDifficulty.entries.toList(),
            itemToString = { difficulty -> getQuizDifficultyAsString(difficulty) },
            value = state.value.quizDifficulty?.let { getQuizDifficultyAsString(it) } ?: "",
            placeholderText = stringResource(com.andef.dailyquiz.core.design.R.string.difficulty),
            onItemClick = { difficulty ->
                onQuizDifficultyExpandedChange(false)
                onQuizDifficultyClick(difficulty)
            },
            expanded = state.value.quizDifficultyExpanded,
            onExpandedChange = onQuizDifficultyExpandedChange
        )
    }
}

@Composable
private fun ColumnScope.TitleAndSubtitleForUiCard() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(com.andef.dailyquiz.core.design.R.string.almost_ready),
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            letterSpacing = 0.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center,
            color = Black
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(com.andef.dailyquiz.core.design.R.string.remains_to_choose_dif),
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 16.sp,
            textAlign = TextAlign.Center,
            color = Black
        )
    }
}