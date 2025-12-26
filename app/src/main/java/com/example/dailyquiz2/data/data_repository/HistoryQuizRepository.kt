package com.example.dailyquiz2.data.data_repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.dailyquiz2.presentation.History_Quiz.history_models
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


import javax.inject.Inject




class HistoryQuizRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
): History_repositore {



    private val historyKey = stringPreferencesKey("history_key")


    override fun getHistory(): Flow<List<history_models>> =
        dataStore.data.map { prefs ->
            val json = prefs[historyKey]


            if (json.isNullOrEmpty()) {
                emptyList()
            } else {

                Json.decodeFromString<List<history_models>>(json)
            }
        }

    // Сохраняем данные в DataStore
    override suspend fun saveHistory(item: history_models) {
        dataStore.edit { prefs ->
            val currentJson = prefs[historyKey]


            val currentList = if (currentJson != null) {
                Json.decodeFromString<MutableList<history_models>>(currentJson)
            } else {
                mutableListOf()
            }


            currentList.add(item)


            prefs[historyKey] = Json.encodeToString(currentList)
        }
    }


    override suspend fun deleteHistory(item: history_models) {
        dataStore.edit { prefs ->
            val currentJson = prefs[historyKey]


            val currentList = if (currentJson != null) {
                Json.decodeFromString<MutableList<history_models>>(currentJson)
            } else {
                mutableListOf<history_models>()
            }


            currentList.remove(item)


            prefs[historyKey] = Json.encodeToString(currentList)
        }
    }
}








