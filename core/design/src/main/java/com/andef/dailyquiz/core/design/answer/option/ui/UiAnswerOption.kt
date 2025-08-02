package com.andef.dailyquiz.core.design.answer.option.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.LightGray
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.answer.option.type.UiAnswerOptionType

@Composable
fun UiAnswerOption(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    type: UiAnswerOptionType,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        border = type.borderColor?.let { BorderStroke(width = 1.dp, color = it) },
        colors = colors(type = type)
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(type.iconResId),
                contentDescription = stringResource(R.string.answer_result_icon)
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start,
                text = text,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight.W400,
                letterSpacing = 0.sp,
                lineHeight = 14.sp
            )
        }
    }
}

private val shape = RoundedCornerShape(size = 16.dp)

private fun getContainerColorByType(type: UiAnswerOptionType) = type.borderColor?.let {
    Color.Transparent
} ?: LightGray

private fun getContentColorByType(type: UiAnswerOptionType) = type.borderColor ?: Black

@Composable
private fun colors(type: UiAnswerOptionType) = CardDefaults.cardColors(
    containerColor = getContainerColorByType(type = type),
    contentColor = getContentColorByType(type = type),
    disabledContainerColor = getContainerColorByType(type = type),
    disabledContentColor = getContentColorByType(type = type)
)