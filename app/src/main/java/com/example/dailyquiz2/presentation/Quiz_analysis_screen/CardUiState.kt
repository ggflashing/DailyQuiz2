package com.example.dailyquiz2.presentation.Quiz_analysis_screen

data class CardUiState(
    val questionText: String,
    val options: List<String>,
    val selectedIndex: Int,
    val correctIndex: Int
)

