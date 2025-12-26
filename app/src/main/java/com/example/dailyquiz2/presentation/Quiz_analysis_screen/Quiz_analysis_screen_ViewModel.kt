package com.example.dailyquiz2.presentation.Quiz_analysis_screen



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz2.presentation.QuizResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import okio.Options
import javax.inject.Inject
import kotlin.collections.mapIndexed


@HiltViewModel
class Quiz_analysis_screen_ViewModel @Inject constructor(

    private val repository: QuizResultRepository


) : ViewModel() {


    val results = repository.results
}