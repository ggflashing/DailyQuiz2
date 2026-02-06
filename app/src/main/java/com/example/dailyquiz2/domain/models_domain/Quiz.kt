package com.example.dailyquiz2.domain.models_domain

class Quiz (

    var failedCode: Int,
    val question: String,
    val difficulty: String,
    val category: String,
    val type: String,
    val answers: List<String>,

    val correctAnswerIndex: Int

)
