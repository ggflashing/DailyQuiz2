package com.example.dailyquiz2.data.data_repository

import com.example.dailyquiz2.presentation.History_Quiz.historyModels
import com.example.dailyquiz2.presentation.History_Quiz.resultHistoryModels
import kotlinx.coroutines.flow.Flow

interface HistoryRepositore  {


    fun getHistory(): Flow<List<historyModels>>

   fun getResults(): Flow<List<resultHistoryModels>>


    suspend fun saveHistory(item: historyModels)

    suspend fun deleteHistory(item: historyModels)

  //  suspend fun deleteHistory_result(item_result: result_history_models)

    suspend fun save_result_history(item_result: resultHistoryModels)

}