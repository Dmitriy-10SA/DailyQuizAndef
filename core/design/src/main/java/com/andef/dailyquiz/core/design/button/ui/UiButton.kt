package com.andef.dailyquiz.core.design.button.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.MediumGray
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.button.type.UiButtonType

@Composable
fun UiButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    type: UiButtonType = UiButtonType.Main,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors(type = type),
        contentPadding = getContentPaddingByType(type = type)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight.W900,
                lineHeight = 16.sp,
                letterSpacing = 0.sp
            )
            if (type is UiButtonType.WithIcon) {
                Icon(
                    painter = type.icon,
                    contentDescription = type.contentDescription,
                )
            }
        }
    }
}

private fun getContentPaddingByType(type: UiButtonType) = when (type) {
    UiButtonType.Main, UiButtonType.Secondary -> PaddingValues(16.dp)
    is UiButtonType.WithIcon -> PaddingValues(12.dp)
}

@Composable
private fun colors(type: UiButtonType) = ButtonDefaults.buttonColors(
    containerColor = type.backgroundColor,
    contentColor = type.contentColor,
    disabledContainerColor = MediumGray,
    disabledContentColor = White
)

private val shape = RoundedCornerShape(16.dp)