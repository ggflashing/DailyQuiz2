package com.example.dailyquiz2.presentation.History_Quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class QuizHistoryNavigationScreen{
   object history : QuizHistoryNavigationScreen()
    object history_result : QuizHistoryNavigationScreen()

}

@HiltViewModel
class HistoryViewModel @Inject constructor (

    private val repository: HistoryQuizRepository


) : ViewModel() {

    private val _uiState = MutableStateFlow(navigateHistoryScreen())

    val uiState = _uiState.asStateFlow()


    private val _screen = MutableStateFlow<QuizHistoryNavigationScreen>(
        QuizHistoryNavigationScreen.history)


    val screen : StateFlow<QuizHistoryNavigationScreen> = _screen

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
        _screen.value = QuizHistoryNavigationScreen.history_result
    }

    fun back() {

        _screen.value = QuizHistoryNavigationScreen.history
        _selectedHistoryId.value = null
    }

    fun deleteHistory(item: historyModels) {
        viewModelScope.launch {
            repository.deleteHistory(item)
        }
    }

    fun navigationResultHistory() {
        _uiState.update { state ->
            state.copy(
                screenState_history_models_result = QuizHistoryNavigationScreen.history

            )

        }
    }

}