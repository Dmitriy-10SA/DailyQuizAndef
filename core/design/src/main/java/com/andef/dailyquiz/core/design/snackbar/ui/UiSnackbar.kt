package com.andef.dailyquiz.core.design.snackbar.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.White

@Composable
fun UiSnackbar(modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackbarHostState
    ) { data ->
        Snackbar(
            shape = shape,
            containerColor = Color(0xFF888888).copy(alpha = 0.9f),
            contentColor = White,
            content = {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    text = data.visuals.message,
                    fontSize = 14.sp,
                    letterSpacing = 0.sp,
                    lineHeight = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}

private val shape = RoundedCornerShape(32.dp)