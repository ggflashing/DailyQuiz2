package com.example.dailyquiz2

import android.content.Context
import androidx.datastore.core.DataStore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import androidx.datastore.preferences.core.Preferences
import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import com.example.dailyquiz2.data.data_repository.HistoryRepositore
import com.example.dailyquiz2.data.datasource.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideHistoryRepository(dataStore: DataStore<Preferences>): HistoryRepositore = HistoryQuizRepository(dataStore)
}