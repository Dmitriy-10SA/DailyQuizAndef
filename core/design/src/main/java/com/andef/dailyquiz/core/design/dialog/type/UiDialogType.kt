package com.andef.dailyquiz.core.design.dialog.type

sealed class UiDialogType {
    data class WithActionButton(val buttonText: String, val onClick: () -> Unit) : UiDialogType()
    data object WithDismissButton : UiDialogType()
}