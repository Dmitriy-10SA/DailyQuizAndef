package com.andef.dailyquiz.core.design.dialog.type

import com.andef.dailyquiz.core.design.dialog.ui.UiDialog

/**
 * Тип диалога
 *
 * @property WithActionButton диалог с кнопкой и возможностью выполнить действие - Pop-up Default в Figma
 * @property WithDismissButton диалог с текстовой кнопкой, где можно только закрыть его - Pop-up Variant5 в Figma
 *
 * @see UiDialog
 */
sealed class UiDialogType {
    data class WithActionButton(val buttonText: String, val onClick: () -> Unit) : UiDialogType()
    data object WithDismissButton : UiDialogType()
}