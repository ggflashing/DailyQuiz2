package com.example.dailyquiz2.presentation.Start_Quiz

import com.example.dailyquiz2.domain.models_domain.Quiz


data class QuizUiState_QuizProgress(
    val quiz: List<Quiz> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val answersHistory: List<Int?> = emptyList(),
    val isLoading: Boolean = true,

    val screenState: QuizScreenState = QuizScreenState.Start,


    val numberQuestion: Int = 1,





)
