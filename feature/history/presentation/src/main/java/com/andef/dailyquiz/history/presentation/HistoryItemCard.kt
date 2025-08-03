package com.andef.dailyquiz.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.card.ui.UiCard
import com.andef.dailyquiz.core.design.rating.ui.UiRating
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.util.Date
import java.util.Locale

@Composable
fun HistoryItemCard(
    modifier: Modifier = Modifier,
    quizId: Long,
    correctAnsCnt: Int,
    date: LocalDate,
    time: LocalTime
) {
    UiCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(com.andef.dailyquiz.core.design.R.string.quiz_title_for_history_card)} $quizId",
                    fontWeight = FontWeight.W700,
                    color = DeepPurple,
                    fontFamily = FontFamily(Font(com.andef.dailyquiz.core.design.R.font.inter)),
                    letterSpacing = 0.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 24.sp,
                    fontSize = 24.sp
                )
                UiRating(rating = correctAnsCnt, starSize = 16.dp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatDateWithoutYearLocalized(date),
                    fontWeight = FontWeight.W400,
                    color = Black,
                    fontFamily = FontFamily(Font(com.andef.dailyquiz.core.design.R.font.inter)),
                    letterSpacing = 0.sp,
                    lineHeight = 12.sp,
                    fontSize = 12.sp
                )
                Text(
                    text = formatTimeLocalized(time),
                    fontWeight = FontWeight.W400,
                    color = Black,
                    fontFamily = FontFamily(Font(com.andef.dailyquiz.core.design.R.font.inter)),
                    letterSpacing = 0.sp,
                    lineHeight = 12.sp,
                    fontSize = 12.sp
                )
            }
        }
    }
}

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

private fun formatTimeLocalized(time: LocalTime): String {
    val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
    return time.format(formatter)
}