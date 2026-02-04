package com.example.dailyquiz2.data.data_repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.dailyquiz2.presentation.History_Quiz.historyModels
import com.example.dailyquiz2.presentation.History_Quiz.resultHistoryModels
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


    override fun getHistory(): Flow<List<historyModels>> =
        dataStore.data.map { prefs ->

            prefs[historyKey]?.let {

                Json.decodeFromString(it)

            } ?: emptyList()
        }

    override fun getResults(): Flow<List<resultHistoryModels>> =

        dataStore.data.map { prefs ->

            prefs[resultKey]?.let {
                Json.decodeFromString(it)

            } ?: emptyList()
        }

    override suspend fun saveHistory(item: historyModels) {

        dataStore.edit { prefs ->

            val list = prefs[historyKey]?.let {

                Json.decodeFromString<List<historyModels>>(it)

            } ?: emptyList()

            prefs[historyKey] = Json.encodeToString(list + item)
        }
    }

    override suspend fun save_result_history(item: resultHistoryModels) {

        dataStore.edit { prefs ->
            val list = prefs[resultKey]?.let {

                Json.decodeFromString<List<resultHistoryModels>>(it)
            } ?: emptyList()

            prefs[resultKey] = Json.encodeToString(list + item)
        }
    }

    override suspend fun deleteHistory(item: historyModels) {
        dataStore.edit { prefs ->

            val history = Json.decodeFromString<List<historyModels>>(prefs[historyKey] ?: "[]")

                .filterNot { it.id_history_model == item.id_history_model }

            val results = Json.decodeFromString<List<resultHistoryModels>>(prefs[resultKey] ?: "[]")

                .filterNot { it.historyId == item.id_history_model }

            prefs[historyKey] = Json.encodeToString(history)

            prefs[resultKey] = Json.encodeToString(results)
        }
    }


}








