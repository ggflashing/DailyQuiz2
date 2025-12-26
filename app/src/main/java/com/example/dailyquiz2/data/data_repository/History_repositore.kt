package com.example.dailyquiz2.data.data_repository

import com.example.dailyquiz2.presentation.History_Quiz.history_models
import kotlinx.coroutines.flow.Flow

interface History_repositore  {


    fun getHistory(): Flow<List<history_models>>


    suspend fun saveHistory(item: history_models)

    suspend fun deleteHistory(item: history_models)

}