package com.andef.dailyquiz.core.design.dialog.type

import com.andef.dailyquiz.core.design.dialog.ui.UiDialog

/**
 * Типы диалога, определяющие содержимое нижней части окна.
 *
 * @property WithActionButton диалог с кнопкой действия (например, "НАЧАТЬ ВИКТОРИНУ") — соответствует Pop-up Default в Figma
 * @property WithDismissButton диалог только с текстовой кнопкой закрытия — соответствует Pop-up Variant5 в Figma
 *
 * @see UiDialog
 */
sealed class UiDialogType {
    /**
     * Диалог с основной кнопкой действия.
     *
     * @param buttonText текст кнопки
     * @param onClick обработчик нажатия кнопки
     */
    data class WithActionButton(val buttonText: String, val onClick: () -> Unit) : UiDialogType()

    /** Диалог с текстовой кнопкой для закрытия */
    data object WithDismissButton : UiDialogType()
}