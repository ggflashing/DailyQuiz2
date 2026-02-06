package com.example.dailyquiz2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailyquiz2.presentation.History_Quiz.navigationHistoryScreen

import com.example.dailyquiz2.presentation.Start_Quiz.ProgressScreen
import com.example.dailyquiz2.presentation.Start_Quiz.startQuiz

object AppRouts {

    const val STARTQUIZ = "startQuiz"

    const val QuizInProgress = "QuizInProgressScreen"

    const val ProgressScreen = "ProgressScreen"

    const val QuizResuiltEndScreen = "QuizResuiltEndScreen"


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

                onNavigateToQUIZStart = {
                    navController.navigate(AppRouts.STARTQUIZ)
                },


                        onNavigateToHistory = {
                    navController.navigate(AppRouts.navigationHistoryScreen)
                },
            )
        }

        composable(AppRouts.navigationHistoryScreen) {
            navigationHistoryScreen(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                navigationHistoryQuiz = {
                    navController.navigate(AppRouts.HISTORYQUIZ)
                },
                navigationHistoryQuizResult = {
                    navController.navigate(AppRouts.HistoryResult)
                },

                onNavigateToQUIZStart = {
                    navController.navigate(AppRouts.STARTQUIZ)
                }
            )
        }
    }
}

