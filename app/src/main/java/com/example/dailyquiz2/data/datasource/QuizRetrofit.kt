package com.example.dailyquiz2.data.datasource

import com.example.dailyquiz2.connect_api_Retrofit.Quiz_respons
import com.example.dailyquiz2.connect_api_Retrofit.ServisApi
import com.example.dailyquiz2.connect_api_Retrofit.connect_quiz_model
import javax.inject.Inject


class QuizRetrofit @Inject constructor(
    private val api: ServisApi

) {

    suspend fun getQustion_code(): Quiz_respons {

        return api.getQuestions()


    }

    suspend fun getfailed_code(): List<Int> {

       return listOf(api.getQuestions().responseCode)
    }


}