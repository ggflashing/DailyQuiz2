package com.example.dailyquiz2.domain.use_case

import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import com.example.dailyquiz2.presentation.History_Quiz.history_models
import com.example.dailyquiz2.presentation.History_Quiz.result_history_models
import javax.inject.Inject


class SaveHistoryQuizUseCase @Inject constructor(
    private val repository: HistoryQuizRepository

) {

    suspend operator fun invoke(history: history_models) {
        repository.saveHistory(history)
    }

    suspend operator fun invoke(history_result: result_history_models) {
        repository.save_result_history(history_result)
    }

}