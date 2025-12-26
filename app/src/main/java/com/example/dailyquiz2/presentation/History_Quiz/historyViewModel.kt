package com.example.dailyquiz2.presentation.History_Quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class HistoryViewModel @Inject constructor (
    private val repository: HistoryQuizRepository
) : ViewModel() {

    private val _history = MutableStateFlow<List<history_models>>(emptyList())
    val history: StateFlow<List<history_models>> = _history

    init {
        viewModelScope.launch {
            repository.getHistory().collect { historyList ->
                _history.value = historyList
            }
        }
    }


    fun saveHistory(item: history_models) {
        viewModelScope.launch {
            repository.saveHistory(item)
        }
    }


    fun deleteHistory(item: history_models) {
        viewModelScope.launch {
            repository.deleteHistory(item)
        }
    }
}