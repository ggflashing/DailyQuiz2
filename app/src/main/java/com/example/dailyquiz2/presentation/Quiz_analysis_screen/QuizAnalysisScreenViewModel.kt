package com.example.dailyquiz2.presentation.Quiz_analysis_screen



import androidx.lifecycle.ViewModel
import com.example.dailyquiz2.presentation.QuizResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class QuizAnalysisScreenViewModel @Inject constructor(

    private val repository: QuizResultRepository


) : ViewModel() {


    val results = repository.results
}