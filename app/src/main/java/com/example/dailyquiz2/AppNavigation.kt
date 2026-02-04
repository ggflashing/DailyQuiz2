package com.example.dailyquiz2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



import com.example.dailyquiz2.presentation.History_Quiz.navigationHistoryScreen


import com.example.dailyquiz2.presentation.Quiz_analysis_screen.Quiz_analysis_screen
import com.example.dailyquiz2.presentation.Start_Quiz.ProgressScreen

import com.example.dailyquiz2.presentation.Start_Quiz.startQuiz



object AppRouts {

    const val STARTQUIZ = "startQuiz"

    const val QuizInProgress = "QuizInProgressScreen"

    const val ProgressScreen = "ProgressScreen"

    const val QuizResuiltEndScreen = "QuizResuiltEndScreen"

    const val QUIZANALYSIS = "quizAnalysis"

    const val HISTORYQUIZ = "historyQuiz"

    const val HistoryResult = "HistoryResult"

    const val navigationHistoryScreen = "navigationHistoryScreen"


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    var navController = rememberNavController()

    val startDestination = AppRouts.STARTQUIZ

    NavHost(

        navController = navController,
        startDestination = startDestination

    ){
        composable(AppRouts.ProgressScreen){
            ProgressScreen(
            //    navController = navController,
             //   onNavigateBack = {
           //         navController.popBackStack()
           //     },

           //     navigation_To_analysis_screen = {
            //        navController.navigate(AppRouts.QUIZ_ANALYSIS)

            //    },
            //    failed = {
           //         navController.popBackStack()
           //     }
            )

        }

        composable(AppRouts.STARTQUIZ) {
            startQuiz(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                onNavigateToQUIZ_ANALYSIS = {
                    navController.navigate(AppRouts.QUIZANALYSIS)
                },

                onNavigateToHistory = {
                    navController.navigate(AppRouts.navigationHistoryScreen)
                }
            )
        }

        composable(AppRouts.QUIZANALYSIS) {
            Quiz_analysis_screen(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                onNavigateToQUIZ_start = {
                    navController.navigate(AppRouts.STARTQUIZ)
                }

            )

        }

        composable(AppRouts.navigationHistoryScreen) {
            navigationHistoryScreen(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                navigation_history_Quiz = {
                    navController.navigate(AppRouts.HISTORYQUIZ)
                },
                navigation_history_Quiz_result = {
                    navController.navigate(AppRouts.HistoryResult)
                },
            )
        }
    }
}

