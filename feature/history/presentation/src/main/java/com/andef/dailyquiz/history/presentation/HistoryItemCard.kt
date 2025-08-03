package com.andef.dailyquiz.history.presentation

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.rating.ui.UiRating
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty
import com.andef.dailyquiz.core.utils.getQuizCategoryAsString
import com.andef.dailyquiz.core.utils.getQuizDifficultyAsString
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
import java.util.Locale

/**
 * Компонент карточки одного элемента истории викторины.
 * Отображает номер викторины, рейтинг (кол-во правильных ответов), дату и время прохождения,
 * категорию и сложность викторины. По долгому нажатию открывается меню действий.
 */
@Composable
fun HistoryItemCard(
    modifier: Modifier = Modifier,
    quizId: Long,
    category: QuizCategory,
    difficulty: QuizDifficulty,
    correctAnsCnt: Int,
    date: LocalDate,
    time: LocalTime,
    onLongClick: () -> Unit,
    onDismiss: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    menuExpanded = true
                    onLongClick()
                }
            )
    ) {
        UiCard {
            HistoryItemMainContent(
                quizId = quizId,
                correctAnsCnt = correctAnsCnt,
                date = date,
                time = time,
                category = category,
                difficulty = difficulty
            )
            HistoryItemMenu(
                menuExpanded = menuExpanded,
                onDismissRequest = {
                    menuExpanded = false
                    onDismiss()
                },
                onDeleteClick = {
                    menuExpanded = false
                    onDismiss()
                    onDeleteClick()
                }
            )
        }
    }
}

/**
 * Основное содержимое карточки истории: номер викторины, рейтинг, дата и время, категория и сложность.
 */
@Composable
private fun HistoryItemMainContent(
    quizId: Long,
    correctAnsCnt: Int,
    date: LocalDate,
    time: LocalTime,
    category: QuizCategory,
    difficulty: QuizDifficulty
) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuizNumberAndRatingRow(quizId = quizId, correctAnsCnt = correctAnsCnt)
        DateAndTimeRow(date = date, time = time)
        QuizCategoryAndDifficultyRow(category = category, difficulty = difficulty)
    }
}

/**
 * Отображает категорию и сложность викторины.
 */
@Composable
private fun QuizCategoryAndDifficultyRow(category: QuizCategory, difficulty: QuizDifficulty) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${stringResource(R.string.category)}:" +
                    " ${getQuizCategoryAsString(category)}",
            fontWeight = FontWeight.W400,
            color = Black,
            fontFamily = FontFamily(Font(R.font.inter)),
            letterSpacing = 0.sp,
            lineHeight = 12.sp,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${stringResource(R.string.difficulty)}:" +
                    " ${getQuizDifficultyAsString(difficulty)}",
            fontWeight = FontWeight.W400,
            color = Black,
            fontFamily = FontFamily(Font(R.font.inter)),
            letterSpacing = 0.sp,
            lineHeight = 12.sp,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Отображает строку с номером викторины и визуальной оценкой (звездами).
 */
@Composable
private fun QuizNumberAndRatingRow(quizId: Long, correctAnsCnt: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${stringResource(R.string.quiz_title_for_history_card)} $quizId",
            fontWeight = FontWeight.W700,
            color = DeepPurple,
            fontFamily = FontFamily(Font(R.font.inter)),
            letterSpacing = 0.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 24.sp,
            fontSize = 24.sp
        )
        UiRating(rating = correctAnsCnt, starSize = 16.dp)
    }
}

/**
 * Отображает дату и время прохождения викторины.
 */
@Composable
private fun DateAndTimeRow(date: LocalDate, time: LocalTime) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formatDateWithoutYearLocalized(date),
            fontWeight = FontWeight.W400,
            color = Black,
            fontFamily = FontFamily(Font(R.font.inter)),
            letterSpacing = 0.sp,
            lineHeight = 12.sp,
            fontSize = 12.sp
        )
        Text(
            text = formatTimeLocalized(time),
            fontWeight = FontWeight.W400,
            color = Black,
            fontFamily = FontFamily(Font(R.font.inter)),
            letterSpacing = 0.sp,
            lineHeight = 12.sp,
            fontSize = 12.sp
        )
    }
}

/**
 * Контекстное меню карточки истории, позволяющее выполнить действия (удалить запись).
 */
@Composable
private fun HistoryItemMenu(
    menuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onDeleteClick: () -> Unit
) {
    DropdownMenu(
        shape = RoundedCornerShape(12.dp),
        containerColor = White,
        expanded = menuExpanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            contentPadding = PaddingValues(12.dp),
            leadingIcon = {
                Icon(
                    tint = Black,
                    painter = painterResource(R.drawable.trash),
                    contentDescription = stringResource(R.string.delete)
                )
            },
            text = {
                Text(
                    modifier = Modifier.padding(start = 24.dp, end = 100.dp),
                    text = stringResource(R.string.delete),
                    fontWeight = FontWeight.W400,
                    color = Black,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    letterSpacing = 0.sp,
                    lineHeight = 14.sp,
                    fontSize = 14.sp
                )
            },
            onClick = onDeleteClick
        )
    }
}

/**
 * Форматирует дату без указания года, локализованно в зависимости от текущей локали устройства.
 *
 * @param date Дата, которую нужно отформатировать (без года).
 *
 * @return Строковое представление даты в формате "день месяц" (например, "3 августа").
 */
private fun formatDateWithoutYearLocalized(date: LocalDate): String {
    val locale = Locale.getDefault()
    return if (locale.language == "ru") {
        val pattern = "d MMMM"
        val sdf = SimpleDateFormat(pattern, locale)
        val instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant()
        sdf.format(Date.from(instant))
    } else {
        val formatter = DateTimeFormatter.ofPattern("d MMMM", locale)
        date.format(formatter)
    }
}

/**
 * Форматирует время в соответствии с текущей локалью устройства.
 * Используется стиль FormatStyle.SHORT (например, "14:30" или "2:30 PM").
 *
 * @param time Время, которое нужно отформатировать.
 *
 * @return Строковое представление времени в кратком формате.
 */
private fun formatTimeLocalized(time: LocalTime): String {
    val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
    return time.format(formatter)
}