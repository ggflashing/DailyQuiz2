package com.example.dailyquiz2.domain.use_case

import com.example.dailyquiz2.data.data_repository.QuizRepository
import com.example.dailyquiz2.domain.models_domain.Quiz
import com.example.dailyquiz2.domain.repository.QuizRepository_Domain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Logics_use @Inject constructor(

    private val repository_get: QuizRepository_Domain

) {


    operator fun invoke(): Flow<List<Quiz>>{
        return repository_get.get_quistion_domain()

    }





}