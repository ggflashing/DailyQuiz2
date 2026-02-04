package com.example.dailyquiz2.domain.use_case

import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import com.example.dailyquiz2.presentation.History_Quiz.historyModels
import com.example.dailyquiz2.presentation.History_Quiz.resultHistoryModels
import javax.inject.Inject


class SaveHistoryQuizUseCase @Inject constructor(
    private val repository: HistoryQuizRepository

) {

    suspend operator fun invoke(history: historyModels) {
        repository.saveHistory(history)
    }

    suspend operator fun invoke(history_result: resultHistoryModels) {
        repository.save_result_history(history_result)
    }

}