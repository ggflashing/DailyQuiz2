package com.example.dailyquiz2.presentation

import com.example.dailyquiz2.presentation.Quiz_analysis_screen.QuestionResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizResultRepository @Inject constructor() {

    private val _results = MutableStateFlow<List<QuestionResult>>(emptyList())
    val results: StateFlow<List<QuestionResult>> = _results

    fun setResults(list: List<QuestionResult>) {
        _results.value = list
    }

    fun clear() {
        _results.value = emptyList()
    }
}