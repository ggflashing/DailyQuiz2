package com.example.dailyquiz2.connect_api_Retrofit

import com.google.gson.annotations.SerializedName

data class Quiz_respons(

    @SerializedName("response_code")
    val responseCode: Int,

    @SerializedName("results")
    val results: List<connect_quiz_model>

)
