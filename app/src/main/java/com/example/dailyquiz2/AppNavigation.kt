package com.example.dailyquiz2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailyquiz2.AppRouts.Quiz_resuilt_end_Screen


import com.example.dailyquiz2.presentation.History_Quiz.history_Quiz
import com.example.dailyquiz2.presentation.Quiz_analysis_screen.Quiz_analysis_screen
import com.example.dailyquiz2.presentation.Start_Quiz.QuizInProgressScreen
import com.example.dailyquiz2.presentation.Start_Quiz.Quiz_result_end_Screen
import com.example.dailyquiz2.presentation.Start_Quiz.start_quiz


object AppRouts {

    const val START_QUIZ = "start_quiz"

    const val QuizInProgress = "QuizInProgressScreen"

    const val Quiz_resuilt_end_Screen = "Quiz_resuilt_end_Screen"

    const val QUIZ_ANALYSIS = "quiz_analysis"

    const val HISTORY_QUIZ = "history_quiz"


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
                    navController.navigate(AppRouts.HISTORY_QUIZ)
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

        composable(AppRouts.HISTORY_QUIZ) {
        history_Quiz(
            navController = navController,
            onNavigateBack = {
                navController.popBackStack()

            },



        )


        }





    }



}

