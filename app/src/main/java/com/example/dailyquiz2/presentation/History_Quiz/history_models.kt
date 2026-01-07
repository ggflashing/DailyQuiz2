package com.example.dailyquiz2.presentation.History_Quiz

import kotlinx.serialization.Serializable


@Serializable
data class history_models(

    val stars: Int,
    val total: Int,
    val month: String,
    val monthNumber: Int,
    val time: String


)
