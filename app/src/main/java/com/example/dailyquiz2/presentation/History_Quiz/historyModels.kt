package com.example.dailyquiz2.presentation.History_Quiz

import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class historyModels(

    val id_history_model: String = UUID.randomUUID().toString(),

    val stars: Int,
    val total: Int,
    val month: String,
    val monthNumber: Int,
    val time: String,


    )
