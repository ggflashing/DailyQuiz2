package com.example.dailyquiz2.data.data_repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.dailyquiz2.NetworkModule
import com.example.dailyquiz2.connect_api_Retrofit.Quiz_respons
import com.example.dailyquiz2.connect_api_Retrofit.ServisApi
import com.example.dailyquiz2.connect_api_Retrofit.connect_quiz_model
import com.example.dailyquiz2.data.datasource.QuizRetrofit
import com.example.dailyquiz2.domain.models_domain.Quiz
import com.example.dailyquiz2.domain.repository.QuizRepository_Domain
import com.example.dailyquiz2.mapper_data.toDomain
import com.example.dailyquiz2.presentation.History_Quiz.history_models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class QuizRepository @Inject constructor(
    private val QuizRetrofit: QuizRetrofit,

   // private val dataStore: DataStore<history_models>

): QuizRepository_Domain {



    override fun get_quistion_domain(): Flow<List<Quiz>> = flow {
        try {
            val response = QuizRetrofit.getQustion_code()
            emit(response.results.map {
                it.toDomain()
            })

        } catch (e: Exception) {
            emit(emptyList())
            println(e.message)

        }
    }

   // override suspend fun saveHistory(item: history_models) {
   //     dataStore.updateData { current ->
   //         current.toBuilder()
   //             .addItems(item.toProto())
   //             .build()
  //      }
 //   }

  //  override fun getHistory(): Flow<List<history_models>> {
  //      dataStore.data.map { list ->
   //         list.itemsList.map { it.toDomain() }
   //     }
  //  }


    //  fun getQuestions(): Flow<List<Quiz>> = flow {
  //      try {
   //         val response = QuizRetrofit.getQustion_code()
   //         emit(response.results.map {
    //            it.toDomain()
   //         })
//
   //     } catch (e: Exception) {
    //        emit(emptyList())
    //        println(e.message)
//
   //     }
//
  //  }


    //  suspend fun get_retrofit_qustion(): List<Quiz>{
//
   //     val response = QuizRetrofit.getQustion_code()
//
   //     return response.results.map {
    //        it.toDomain()
   //     }




    }







