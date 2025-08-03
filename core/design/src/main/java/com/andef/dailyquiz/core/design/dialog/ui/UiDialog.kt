package com.andef.dailyquiz.core.design.dialog.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.R
import com.andef.dailyquiz.core.design.White
import com.andef.dailyquiz.core.design.button.ui.UiButton
import com.andef.dailyquiz.core.design.dialog.type.UiDialogType
import com.andef.dailyquiz.core.design.text.button.ui.UiTextButton

/**
 * Диалог (AlertDialog) с двумя вариантами поведения:
 * с кнопкой действия или с кнопкой закрытия.
 *
 * Отображает заголовок, подзаголовок и кнопку в зависимости от [UiDialogType].
 *
 * @param type тип диалога (с действием или только для закрытия)
 * @param isVisible флаг видимости диалога
 * @param title заголовок диалога
 * @param subTitle подзаголовок или пояснительный текст
 * @param onDismissRequest обработчик закрытия окна
 *
 * @see UiDialogType
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiDialog(
    type: UiDialogType,
    isVisible: Boolean,
    title: String,
    subTitle: String,
    onDismissRequest: () -> Unit
) {
    if (isVisible) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontSize = 24.sp,
                    letterSpacing = 0.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = subTitle,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontSize = 16.sp,
                    letterSpacing = 0.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                ConfirmButtonForType(type = type, onDismissRequest = onDismissRequest)
            },
            shape = shape,
            textContentColor = Black,
            titleContentColor = Black,
            containerColor = White,
        )
    }
}

@Composable
private fun ConfirmButtonForType(type: UiDialogType, onDismissRequest: () -> Unit) {
    when (type) {
        is UiDialogType.WithActionButton -> {
            UiButton(
                onClick = type.onClick,
                modifier = Modifier.fillMaxWidth(),
                text = type.buttonText
            )
        }

        UiDialogType.WithDismissButton -> {
            UiTextButton(text = stringResource(R.string.close), onClick = onDismissRequest)
        }
    }
}

private val shape = RoundedCornerShape(size = 40.dp)