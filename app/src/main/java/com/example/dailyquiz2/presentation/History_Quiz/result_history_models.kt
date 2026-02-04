package com.example.dailyquiz2.presentation.History_Quiz

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class result_history_models(

    val id: String = UUID.randomUUID().toString(),

    val historyId: String,

    val questionIndex: Int,

    val totalQuestions: Int,

    val questionText: String,

    val options: List<String>,

    val selectedIndex: Int,

    val correctIndex: Int,



   // val screenState_history_models_result: Quiz_history_navigation_screen = Quiz_history_navigation_screen.history
)