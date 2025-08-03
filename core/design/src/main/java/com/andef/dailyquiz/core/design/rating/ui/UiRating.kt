package com.andef.dailyquiz.core.design.rating.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andef.dailyquiz.core.design.R

/**
 * Компонент UI для показа рейтинга (5 звезд)
 *
 * @
 */
@Composable
fun UiRating(modifier: Modifier = Modifier, rating: Int, starSize: Dp = 52.dp) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            Image(
                modifier = Modifier.size(starSize),
                painter = painterResource(
                    when (index + 1 <= rating) {
                        true -> R.drawable.active_star
                        false -> R.drawable.inactive_star
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.star_icon)
            )
            if (index < 4) Spacer(modifier = Modifier.width(8.dp))
        }
    }
}