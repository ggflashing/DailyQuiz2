package com.example.dailyquiz2.presentation.History_Quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun navigationHistoryScreen(

    navController: NavHostController,

    onNavigateBack: () -> Unit,

    viewModel: HistoryViewModel = hiltViewModel(),

    navigation_history_Quiz: () -> Unit,

    navigation_history_Quiz_result: () -> Unit

) {
    val screen by viewModel.screen.collectAsState()

    val history by viewModel.history.collectAsState()

    val results by viewModel.selectedResults.collectAsState()



    when (screen) {

        QuizHistoryNavigationScreen.history -> {
            historyQuiz(
                navController = navController,
                historyModels = history,
                onOpenResult = { id ->
                    viewModel.openResult(id)
                },
                onDelete = { item ->
                    viewModel.deleteHistory(item)
                },
                navigate_result_histroy = {},
                onNavigateBack = onNavigateBack
            )
        }

        QuizHistoryNavigationScreen.history_result -> {
            resultHistory(
                navController = navController,
                results = results,
                onNavigateBack = {viewModel.back()}
            )
        }
    }
}



