package com.example.dailyquiz2.presentation.mapper_Start_Quiz

import com.example.dailyquiz2.presentation.Quiz_analysis_screen.CardUiState

import com.example.dailyquiz2.presentation.Quiz_analysis_screen.QuestionResult



fun QuestionResult.toCardUiState(): CardUiState {
    return CardUiState(
        questionText = this.questionText,
        options = this.options,
        selectedIndex = this.selectedIndex,
        correctIndex = this.correctIndex
    )
}