package com.andef.dailyquiz.quiz.presentation.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.andef.dailyquiz.core.design.MediumGray
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.Yellow
import com.andef.dailyquiz.core.design.answer.option.type.UiAnswerOptionType
import com.andef.dailyquiz.core.design.answer.option.ui.UiAnswerOption
import com.andef.dailyquiz.core.design.button.type.UiButtonType
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.rating.ui.UiRating
import com.andef.dailyquiz.quiz.domain.entities.Question

@Composable
fun ResultScreen(
    correctAnsCnt: Int,
    userAnswers: Map<Int, String>,
    questions: List<Question>,
    shuffledAnswers: List<List<String>>,
    onRetryClick: () -> Unit
) {
    LazyColumn {
        item { Spacer(modifier = Modifier.statusBarsPadding()) }
        item { Spacer(modifier = Modifier.height(38.dp)) }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.results),
                fontSize = 32.sp,
                fontWeight = FontWeight.W900,
                letterSpacing = 0.sp,
                lineHeight = 32.sp,
                textAlign = TextAlign.Center,
                color = White
            )
        }
        item {
            ResultTopCard(
                correctAnsCnt = correctAnsCnt,
                questions = questions,
                onRetryClick = onRetryClick
            )
        }
        questions.forEachIndexed { index, question ->
            item {
                if (index != 0) Spacer(modifier = Modifier.height(24.dp))
                QuestionDescriptionCard(
                    index = index,
                    question = question,
                    questions = questions,
                    userAnswers = userAnswers,
                    shuffledAnswers = shuffledAnswers
                )
            }
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item {
            UiButton(
                text = stringResource(R.string.retry),
                onClick = onRetryClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 105.dp)
                    .padding(horizontal = 50.dp),
                type = UiButtonType.Secondary
            )
        }
        item { Spacer(modifier = Modifier.navigationBarsPadding()) }
    }
}

@Composable
private fun QuestionDescriptionCard(
    index: Int,
    question: Question,
    questions: List<Question>,
    userAnswers: Map<Int, String>,
    shuffledAnswers: List<List<String>>
) {
    UiCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${stringResource(R.string.question)} ${index + 1} " +
                            "${stringResource(R.string.from)} ${questions.size}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    letterSpacing = 0.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center,
                    color = MediumGray
                )
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.right_radio_button),
                    contentDescription = stringResource(R.string.answer_result_icon)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = question.text,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                letterSpacing = 0.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center,
                color = Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            shuffledAnswers[index].forEachIndexed { answerIndex, answer ->
                if (answerIndex != 0) Spacer(modifier = Modifier.height(16.dp))
                UiAnswerOption(
                    modifier = Modifier.fillMaxWidth(),
                    text = question.text,
                    enabled = false,
                    type = when {
                        userAnswers[index] == answer && answer == question.correctAnswer -> UiAnswerOptionType.Right
                        userAnswers[index] == answer && answer != question.correctAnswer -> UiAnswerOptionType.Wrong
                        else -> UiAnswerOptionType.Default
                    },
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun ResultTopCard(correctAnsCnt: Int, questions: List<Question>, onRetryClick: () -> Unit) {
    UiCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(bottom = 13.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiRating(
                rating = correctAnsCnt,
                modifier = Modifier.fillMaxWidth(),
                starSize = 46.dp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$correctAnsCnt ${stringResource(R.string.from)} ${questions.size}",
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 0.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                color = Yellow
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = getResultTitle(correctAnsCnt),
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 0.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = getResultDescription(correctAnsCnt),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                color = Black
            )
            Spacer(modifier = Modifier.height(52.dp))
            UiButton(
                onClick = onRetryClick,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.retry)
            )
        }
    }
}