package com.example.dailyquiz2.connect_api_Retrofit

import retrofit2.http.GET

interface ServisApi {

    @GET("api.php?amount=5&category=20&difficulty=easy&type=multiple")
    suspend fun getQuestions(): QuizRespons


}