package com.example.dailyquiz2.presentation.Start_Quiz


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun startQuiz(


    viewModel: Start_quiz_ViewModel = hiltViewModel(),
    navController: NavHostController,

    onNavigateBack: () -> Unit,

    onNavigateToQUIZStart: () -> Unit,

    onNavigateToHistory: () -> Unit

){
    val state by viewModel.uiState.collectAsState()
    val screenState = viewModel.uiState.collectAsState().value.screenState

    when (screenState) {

        QuizScreenState.Start -> {
            StartScreen(
                navController = { onNavigateToHistory() },
                StartedClick = { viewModel.onStartClicked() },
                failed = state,
            )
        }

        QuizScreenState.Loading -> {
            ProgressScreen()
        }

        is QuizScreenState.Progress -> {
            QuizInProgressScreen(
                state = state,
                onAnswerSelected = { index ->
                    viewModel.onAnswerSelected(index)
                },
                onNext = {
                    viewModel.onNextQuestion()
                },
                onNavigateBack = {viewModel.navigation_start()},
            )
        }

        is QuizScreenState.Result -> {
            QuizResultEndScreen(
                correctAnswers = state.quiz.indices.count { i ->
                    state.answersHistory.getOrNull(i) == state.quiz[i].correctAnswerIndex
                },
                totalAnswers = state.quiz.size,
                onRestart = {
                    viewModel.navigationQuizStart()
                    onNavigateToQUIZStart()

                },
                onNavigateBack = onNavigateBack,
            )
        }


    }



}




