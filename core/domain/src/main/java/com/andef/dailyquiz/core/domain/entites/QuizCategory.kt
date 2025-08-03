package com.andef.dailyquiz.core.domain.entites

/**
 * Категория викторины
 *
 * @property apiId идентификатор для отправки запроса к API (указываем категорию так)
 */
enum class QuizCategory(val apiId: Int) {
    GENERAL_KNOWLEDGE(apiId = 9),
    ENTERTAINMENT_BOOKS(apiId = 10),
    ENTERTAINMENT_FILM(apiId = 11),
    ENTERTAINMENT_MUSIC(apiId = 12),
    ENTERTAINMENT_MUSICALS_THEATRES(apiId = 13),
    ENTERTAINMENT_TELEVISION(apiId = 14),
    ENTERTAINMENT_VIDEO_GAMES(apiId = 15),
    ENTERTAINMENT_BOARD_GAMES(apiId = 16),
    SCIENCE_NATURE(apiId = 17),
    SCIENCE_COMPUTERS(apiId = 18),
    SCIENCE_MATHEMATICS(apiId = 19),
    MYTHOLOGY(apiId = 20),
    SPORTS(apiId = 21),
    GEOGRAPHY(apiId = 22),
    HISTORY(apiId = 23),
    POLITICS(apiId = 24),
    ART(apiId = 25),
    CELEBRITIES(apiId = 26),
    ANIMALS(apiId = 27),
    VEHICLES(apiId = 28),
    ENTERTAINMENT_COMICS(apiId = 29),
    SCIENCE_GADGETS(apiId = 30),
    ENTERTAINMENT_JAPANESE_ANIME_MANGA(apiId = 31),
    ENTERTAINMENT_CARTOON_ANIMATIONS(apiId = 32)
}