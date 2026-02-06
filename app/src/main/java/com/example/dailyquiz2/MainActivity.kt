package com.example.dailyquiz2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.dailyquiz2.ui.theme.DailyQuiz2Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val systemUiController = rememberSystemUiController()
            val darkIcons = false

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color(0xFF6D67FF),
                    darkIcons = darkIcons
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0xFF6D67FF),
                    darkIcons = darkIcons
                )
            }

            DailyQuiz2Theme {
                Surface (

                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ){
                    AppNavigation()
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                }
            }
        }
    }
}

