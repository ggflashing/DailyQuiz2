package com.example.dailyquiz2.mapper_data

import android.util.Log
import com.example.dailyquiz2.connect_api_Retrofit.connect_quiz_model
import com.example.dailyquiz2.domain.models_domain.Quiz

private const val BUG = "QUIZ_DEBUG"

fun connect_quiz_model.toDomain(): Quiz {

    val allAnswers = (incorrectAnswers + correctAnswer).shuffled()

    val correctIndex = allAnswers.indexOf(correctAnswer)

    return Quiz(
        question = this.question,
        answers = allAnswers,
        correctAnswerIndex = correctIndex,
        category = this.category,
        type = this.type,
        difficulty = this.difficulty,

    )
}