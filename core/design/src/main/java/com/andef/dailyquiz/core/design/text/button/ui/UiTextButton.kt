package com.andef.dailyquiz.core.design.text.button.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.IndigoBlue
import com.andef.dailyquiz.core.design.R

@Composable
fun UiTextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        shape = textButtonShape,
        colors = textButtonColors()
    ) {
        Text(
            text = "Закрыть",
            fontSize = 16.sp,
            letterSpacing = 0.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily(Font(R.font.inter))
        )
    }
}

@Composable
private fun textButtonColors() = ButtonDefaults.textButtonColors(
    containerColor = Color.Transparent,
    contentColor = IndigoBlue
)

private val textButtonShape = RoundedCornerShape(16.dp)