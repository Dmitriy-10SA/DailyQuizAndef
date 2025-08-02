package com.andef.dailyquiz.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.dailyquiz.core.domain.entites.QuizCategory

@Composable
fun getQuizCategoryAsString(category: QuizCategory) = when (category) {
    QuizCategory.GENERAL_KNOWLEDGE -> stringResource(com.andef.dailyquiz.core.design.R.string.category_general_knowledge)
    QuizCategory.ENTERTAINMENT_BOOKS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_books)
    QuizCategory.ENTERTAINMENT_FILM -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_film)
    QuizCategory.ENTERTAINMENT_MUSIC -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_music)
    QuizCategory.ENTERTAINMENT_MUSICALS_THEATRES -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_musicals_theatres)
    QuizCategory.ENTERTAINMENT_TELEVISION -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_television)
    QuizCategory.ENTERTAINMENT_VIDEO_GAMES -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_video_games)
    QuizCategory.ENTERTAINMENT_BOARD_GAMES -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_board_games)
    QuizCategory.SCIENCE_NATURE -> stringResource(com.andef.dailyquiz.core.design.R.string.category_science_nature)
    QuizCategory.SCIENCE_COMPUTERS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_science_computers)
    QuizCategory.SCIENCE_MATHEMATICS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_science_mathematics)
    QuizCategory.MYTHOLOGY -> stringResource(com.andef.dailyquiz.core.design.R.string.category_mythology)
    QuizCategory.SPORTS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_sports)
    QuizCategory.GEOGRAPHY -> stringResource(com.andef.dailyquiz.core.design.R.string.category_geography)
    QuizCategory.HISTORY -> stringResource(com.andef.dailyquiz.core.design.R.string.history)
    QuizCategory.POLITICS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_politics)
    QuizCategory.ART -> stringResource(com.andef.dailyquiz.core.design.R.string.category_art)
    QuizCategory.CELEBRITIES -> stringResource(com.andef.dailyquiz.core.design.R.string.category_celebrities)
    QuizCategory.ANIMALS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_animals)
    QuizCategory.VEHICLES -> stringResource(com.andef.dailyquiz.core.design.R.string.category_vehicles)
    QuizCategory.ENTERTAINMENT_COMICS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_comics)
    QuizCategory.SCIENCE_GADGETS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_science_gadgets)
    QuizCategory.ENTERTAINMENT_JAPANESE_ANIME_MANGA -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_japanese_anime_manga)
    QuizCategory.ENTERTAINMENT_CARTOON_ANIMATIONS -> stringResource(com.andef.dailyquiz.core.design.R.string.category_entertainment_cartoon_animations)
}