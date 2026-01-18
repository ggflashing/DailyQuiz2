package com.example.dailyquiz2

import android.content.Context
import androidx.datastore.core.DataStore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import com.example.dailyquiz2.data.data_repository.History_repositore
import com.example.dailyquiz2.data.datasource.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        context.dataStore


    @Provides
    @Singleton
    fun provideHistoryRepository(
        dataStore: DataStore<Preferences>
    ): History_repositore =
        HistoryQuizRepository(dataStore)
}