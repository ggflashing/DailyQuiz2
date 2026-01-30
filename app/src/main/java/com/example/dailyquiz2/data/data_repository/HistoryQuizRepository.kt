package com.example.dailyquiz2.data.data_repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.dailyquiz2.presentation.History_Quiz.history_models
import com.example.dailyquiz2.presentation.History_Quiz.result_history_models
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import javax.inject.Inject

class HistoryQuizRepository @Inject constructor(

    private val dataStore: DataStore<Preferences>

): History_repositore {


    private val historyKey = stringPreferencesKey("history_key")
    private val resultKey = stringPreferencesKey("history_result_key")


    override fun getHistory(): Flow<List<history_models>> =
        dataStore.data.map { prefs ->

            prefs[historyKey]?.let {

                Json.decodeFromString(it)

            } ?: emptyList()
        }

    override fun getResults(): Flow<List<result_history_models>> =

        dataStore.data.map { prefs ->

            prefs[resultKey]?.let {
                Json.decodeFromString(it)

            } ?: emptyList()
        }

    override suspend fun saveHistory(item: history_models) {

        dataStore.edit { prefs ->

            val list = prefs[historyKey]?.let {

                Json.decodeFromString<List<history_models>>(it)

            } ?: emptyList()

            prefs[historyKey] = Json.encodeToString(list + item)
        }
    }

    override suspend fun save_result_history(item: result_history_models) {

        dataStore.edit { prefs ->
            val list = prefs[resultKey]?.let {

                Json.decodeFromString<List<result_history_models>>(it)
            } ?: emptyList()

            prefs[resultKey] = Json.encodeToString(list + item)
        }
    }

    override suspend fun deleteHistory(item: history_models) {
        dataStore.edit { prefs ->

            val history = Json.decodeFromString<List<history_models>>(prefs[historyKey] ?: "[]")

                .filterNot { it.id_history_model == item.id_history_model }

            val results = Json.decodeFromString<List<result_history_models>>(prefs[resultKey] ?: "[]")

                .filterNot { it.historyId == item.id_history_model }

            prefs[historyKey] = Json.encodeToString(history)

            prefs[resultKey] = Json.encodeToString(results)
        }
    }


}








