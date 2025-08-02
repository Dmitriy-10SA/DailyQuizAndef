package com.andef.dailyquiz.core.design.menu.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType.Companion.PrimaryNotEditable
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.dailyquiz.core.design.Black
import com.andef.dailyquiz.core.design.DeepPurple
import com.andef.dailyquiz.core.design.LightGray
import com.andef.dailyquiz.core.design.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> UiMenu(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemToString: @Composable (T) -> String,
    value: String,
    placeholderText: String,
    onItemClick: (T) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = onExpandedChange) {
        TextField(
            modifier = modifier.menuAnchor(PrimaryNotEditable),
            value = value,
            onValueChange = {},
            placeholder = {
                if (value.isEmpty()) {
                    Text(
                        text = placeholderText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp
                    )
                }
            },
            label = if (value.isNotEmpty()) {
                {
                    Text(
                        text = placeholderText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp
                    )
                }
            } else null,
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        when (expanded) {
                            true -> R.drawable.arrow_up
                            false -> R.drawable.arrow_down
                        }
                    ),
                    contentDescription = stringResource(R.string.open_close_menu)
                )
            },
            singleLine = true,
            readOnly = true,
            shape = textFieldShape(expanded = expanded),
            colors = textFieldColors(),
            textStyle = TextStyle(
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontSize = 16.sp,
                letterSpacing = 0.sp,
                lineHeight = 16.sp
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            shape = menuShape,
            containerColor = LightGray,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = { onItemClick(item) },
                    text = {
                        Text(
                            text = itemToString(item),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight.W400,
                            letterSpacing = 0.sp,
                            lineHeight = 14.sp
                        )
                    },
                    colors = menuItemColors(),
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
private fun animateBottomCornersForTextField(expanded: Boolean) = animateDpAsState(
    targetValue = when (expanded) {
        true -> 0.dp
        false -> 16.dp
    },
    animationSpec = tween(durationMillis = 100)
)

@Composable
private fun textFieldShape(expanded: Boolean) = RoundedCornerShape(
    topEnd = 16.dp,
    topStart = 16.dp,
    bottomEnd = animateBottomCornersForTextField(expanded = expanded).value,
    bottomStart = animateBottomCornersForTextField(expanded = expanded).value
)

private val menuShape = RoundedCornerShape(
    topEnd = 0.dp,
    topStart = 0.dp,
    bottomEnd = 16.dp,
    bottomStart = 16.dp
)

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = Black,
    unfocusedTextColor = Black,
    disabledTextColor = Black,
    focusedContainerColor = LightGray,
    disabledContainerColor = LightGray,
    unfocusedContainerColor = LightGray,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedTrailingIconColor = DeepPurple,
    disabledTrailingIconColor = DeepPurple,
    unfocusedTrailingIconColor = DeepPurple,
    disabledPlaceholderColor = DeepPurple,
    focusedPlaceholderColor = DeepPurple,
    unfocusedPlaceholderColor = DeepPurple,
    disabledLabelColor = DeepPurple,
    focusedLabelColor = DeepPurple,
    unfocusedLabelColor = DeepPurple
)

@Composable
private fun menuItemColors() = MenuDefaults.itemColors(textColor = Black)