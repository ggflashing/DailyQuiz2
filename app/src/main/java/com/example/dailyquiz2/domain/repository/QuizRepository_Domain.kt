package com.example.dailyquiz2.domain.repository

import com.example.dailyquiz2.domain.models_domain.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository_Domain {

    fun get_quistion_domain(): Flow<List<Quiz>>

  //  suspend fun saveHistory(item: history_models)

  //  fun getHistory(): Flow<List<history_models>>

}