package com.example.dailyquiz2.connect_api_Retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class connect_quiz_model(
    @SerializedName("category")
    @Expose
    val category: String,

    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("difficulty")
    @Expose
    val difficulty: String,

    @SerializedName("question")
    @Expose
    val question: String,

    @SerializedName("correct_answer")
    @Expose
    val correctAnswer: String,

    @SerializedName("incorrect_answers")
    @Expose
    val incorrectAnswers: List<String>


)
