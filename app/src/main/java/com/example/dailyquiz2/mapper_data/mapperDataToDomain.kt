package com.example.dailyquiz2.mapper_data

import com.example.dailyquiz2.connect_api_Retrofit.connectQuizModel
import com.example.dailyquiz2.domain.models_domain.Quiz

private const val BUG = "QUIZ_DEBUG"

fun connectQuizModel.toDomain(): Quiz {

    val allAnswers = (incorrectAnswers + correctAnswer).shuffled()

    val correctIndex = allAnswers.indexOf(correctAnswer)

    return Quiz(
        question = this.question,
        answers = allAnswers,
        correctAnswerIndex = correctIndex,
        category = this.category,
        type = this.type,
        difficulty = this.difficulty,
        failedCode = this.responseCode

    )
}