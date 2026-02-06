package com.example.dailyquiz2.data.data_repository

import com.example.dailyquiz2.data.datasource.QuizRetrofit
import com.example.dailyquiz2.domain.models_domain.Quiz
import com.example.dailyquiz2.domain.repository.QuizRepositoryDomain
import com.example.dailyquiz2.mapper_data.toDomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class QuizRepository @Inject constructor(
    private val QuizRetrofit: QuizRetrofit,

   // private val dataStore: DataStore<history_models>

): QuizRepositoryDomain {



    override fun get_quistion_domain(): Flow<List<Quiz>> = flow {

            val response = QuizRetrofit.getQustion_code()
            emit(response.results.map {
                it.toDomain()
            })

    }.catch { e->  // Здесь заменил ловлю responseCode чтобы приложение не крашилось если была ошибка
        emit(emptyList())
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







