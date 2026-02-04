package com.example.dailyquiz2.presentation.History_Quiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import com.example.dailyquiz2.presentation.Start_Quiz.QuizScreenState
import com.example.dailyquiz2.presentation.Start_Quiz.QuizUiState_QuizProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class Quiz_history_navigation_screen{

   object history : Quiz_history_navigation_screen()


    object history_result : Quiz_history_navigation_screen()


}

@HiltViewModel
class HistoryViewModel @Inject constructor (


    private val repository: HistoryQuizRepository


) : ViewModel() {

    private val _uiState = MutableStateFlow(navigate_history_screen())

    val uiState = _uiState.asStateFlow()

    private val _screen = MutableStateFlow<Quiz_history_navigation_screen>(
        Quiz_history_navigation_screen.history)

    val screen : StateFlow<Quiz_history_navigation_screen> = _screen

    private val _selectedHistoryId = MutableStateFlow<String?>(null)

    val history = repository.getHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val allResults = repository.getResults()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val selectedResults = combine(allResults, _selectedHistoryId) { results, id ->
        if (id == null) emptyList()
        else results.filter { it.historyId == id }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun openResult(historyId: String) {
        _selectedHistoryId.value = historyId
        _screen.value = Quiz_history_navigation_screen.history_result
    }

    fun back() {

        _screen.value = Quiz_history_navigation_screen.history
        _selectedHistoryId.value = null
    }

    fun deleteHistory(item: history_models) {
        viewModelScope.launch {
            repository.deleteHistory(item)
        }
    }

    fun navigation_result_history() {
        _uiState.update { state ->
            state.copy(
                screenState_history_models_result = Quiz_history_navigation_screen.history

            )

        }
    }

}