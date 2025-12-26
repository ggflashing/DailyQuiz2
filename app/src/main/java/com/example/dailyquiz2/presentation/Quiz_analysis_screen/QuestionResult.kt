package com.example.dailyquiz2.presentation.Quiz_analysis_screen

data class QuestionResult(

    val questionIndex: Int,

    val totalQuestions: Int,

    val questionText: String,

    val options: List<String>,

    val selectedIndex: Int,

    val correctIndex: Int

)
