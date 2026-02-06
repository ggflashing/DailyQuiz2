package com.example.dailyquiz2.data.datasource

import com.example.dailyquiz2.connect_api_Retrofit.QuizRespons
import com.example.dailyquiz2.connect_api_Retrofit.ServisApi
import javax.inject.Inject



sealed class QuizApiResult {

    data class Success(val data: QuizRespons) : QuizApiResult()

    data class Error(val message: String, val code: Int) : QuizApiResult()

}

class QuizRetrofit @Inject constructor(
    private val api: ServisApi

) {
    suspend fun getQustion_code(): QuizRespons {

        return api.getQuestions()

    }

   // suspend fun getfailed_code(): List<Int> {   //Не разобрался как ловить ловить responseCode заменил по другому
//
   //    return listOf(api.getQuestions().responseCode)
  //  }

}