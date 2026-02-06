package com.example.dailyquiz2.connect_api_Retrofit

import com.google.gson.annotations.SerializedName

data class QuizRespons(

    @SerializedName("results")
    val results: List<connectQuizModel>

)
