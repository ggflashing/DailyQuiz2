package com.example.dailyquiz2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import com.example.dailyquiz2.presentation.History_Quiz.history_Quiz
import com.example.dailyquiz2.presentation.History_Quiz.navigation_history_screen
import com.example.dailyquiz2.presentation.History_Quiz.result_history
import com.example.dailyquiz2.presentation.Quiz_analysis_screen.Quiz_analysis_screen
import com.example.dailyquiz2.presentation.Start_Quiz.Progress_screen
import com.example.dailyquiz2.presentation.Start_Quiz.start_quiz


object AppRouts {

    const val START_QUIZ = "start_quiz"

    const val QuizInProgress = "QuizInProgressScreen"

    const val Progress_screen = "Progress_screen"

    const val Quiz_resuilt_end_Screen = "Quiz_resuilt_end_Screen"

    const val QUIZ_ANALYSIS = "quiz_analysis"

    const val HISTORY_QUIZ = "history_quiz"

    const val History_result = "History_result"

    const val navigation_history_screen = "navigation_history_screen"


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    var navController = rememberNavController()

    val startDestination = AppRouts.START_QUIZ


    NavHost(

        navController = navController,
        startDestination = startDestination

    ){


        composable(AppRouts.Progress_screen){
            Progress_screen(
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

        composable(AppRouts.START_QUIZ) {
            start_quiz(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                onNavigateToQUIZ_ANALYSIS = {
                    navController.navigate(AppRouts.QUIZ_ANALYSIS)
                },

                onNavigateToHistory = {
                    navController.navigate(AppRouts.navigation_history_screen)
                }


            )


        }

        composable(AppRouts.QUIZ_ANALYSIS) {
            Quiz_analysis_screen(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                onNavigateToQUIZ_start = {
                    navController.navigate(AppRouts.START_QUIZ)
                }

            )

        }


        composable(AppRouts.navigation_history_screen) {
            navigation_history_screen(
                navController = navController,
                onNavigateBack = {
                    navController.popBackStack()

                },
                navigation_history_Quiz = {
                    navController.navigate(AppRouts.HISTORY_QUIZ)
                },
                navigation_history_Quiz_result = {
                    navController.navigate(AppRouts.History_result)
                },
            )


        }


    }

}

