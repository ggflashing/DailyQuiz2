package com.example.dailyquiz2.domain.use_case

import com.example.dailyquiz2.domain.models_domain.Quiz
import com.example.dailyquiz2.domain.repository.QuizRepositoryDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogicsUse @Inject constructor(

    private val repository_get: QuizRepositoryDomain

) {

    operator fun invoke(): Flow<List<Quiz>>{
        return repository_get.get_quistion_domain()

    }

}