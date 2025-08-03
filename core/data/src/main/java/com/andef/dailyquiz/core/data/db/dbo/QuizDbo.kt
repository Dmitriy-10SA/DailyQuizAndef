package com.andef.dailyquiz.core.data.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andef.dailyquiz.core.domain.entites.QuizCategory
import com.andef.dailyquiz.core.domain.entites.QuizDifficulty

/**
 * Сущность викторина для БД
 *
 * @property id идентификатор (автоинкриментируемый)
 * @property correctAnswersCnt кол-во правильных ответов пользователя в данной викторине
 * @property date дата, в которую пользователь закончил викторину
 * @property time время, в которое пользователь закончил викторину
 * @property category категория викторины
 * @property difficulty сложность викторины
 *
 * @see QuizCategory
 * @see QuizDifficulty
 */
@Entity(tableName = "quiz")
data class QuizDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "correct_answers_cnt")
    val correctAnswersCnt: Int,
    @ColumnInfo(name = "finished_date")
    val date: String,
    @ColumnInfo(name = "finished_time")
    val time: String,
    val category: QuizCategory,
    val difficulty: QuizDifficulty
)
