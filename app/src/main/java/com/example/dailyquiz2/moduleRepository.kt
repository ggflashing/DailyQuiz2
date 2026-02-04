package com.example.dailyquiz2

import com.example.dailyquiz2.data.data_repository.QuizRepository
import com.example.dailyquiz2.domain.repository.QuizRepository_Domain
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class moduleRepository {


    @Binds
    abstract fun bindQuizRepository(
        repository_module: QuizRepository
    ): QuizRepository_Domain


}