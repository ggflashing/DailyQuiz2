package com.example.dailyquiz2.presentation.Start_Quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz2.R


@Composable
fun Quiz_result_end_Screen(


    correctAnswers: Int,

    totalAnswers: Int,

    onRestart: () -> Unit,
    onNavigateBack: () -> Unit,


    ) {



    val stars = correctAnswers.coerceIn(0,totalAnswers)


    // val state by viewModel.uiState.collectAsState()

    // val correctCount = state.quiz.indices.count { index ->
    //      state.answersHistory[index] == state.quiz[index].correctAnswerIndex
    //  }

    //  val stars = correctCount

    Scaffold(containerColor = Color.White) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(Color(0xFF7067FF), shape = RoundedCornerShape(1.dp))
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(
                text = "Результаты",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(top = 30.dp))


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 270.dp)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    repeat(totalAnswers) { index ->
                        Icon(
                            painter = painterResource(
                                if (index < stars)
                                    R.drawable.active_star
                                else
                                    R.drawable.inactive_star
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = Color.Unspecified

                        )
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)


                ) {


                    Text(
                        text = "$correctAnswers из 5",
                        fontSize = 20.sp,
                        color = Color(0xFFFFB800),
                        fontWeight = FontWeight.Bold
                    )


                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally



                ) {
                    Text(
                        text = stringResource(
                            id = when (stars) {
                                5 -> R.string.Heading_5
                                4 -> R.string.Heading_4
                                3 -> R.string.Heading_3
                                2 -> R.string.Heading_2
                                1 -> R.string.Heading_1
                                else -> R.string.Heading_0
                            }

                        ),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = stringResource(
                            id = when (stars) {
                                5 -> R.string.Subtitle_5
                                4 -> R.string.Subtitle_4
                                3 -> R.string.Subtitle_3
                                2 -> R.string.Subtitle_2
                                1 -> R.string.Subtitle_1
                                else -> R.string.Subtitle_0
                            }
                        ),
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                }

                Spacer(Modifier.height(25.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF7067FF),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            onRestart()
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "НАЧАТЬ ЗАНОВО",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }


            }


        }


    }


}