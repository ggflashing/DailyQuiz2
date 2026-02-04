package com.example.dailyquiz2.presentation.History_Quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavController
import com.example.dailyquiz2.R


@Composable
fun result_history (
    navController: NavController,

    results: List<result_history_models>,

    onNavigateBack: () -> Unit,



    ) {







    val correctAnswers = results.count { it.selectedIndex == it.correctIndex }

    val totalAnswers = results.size

    val stars = correctAnswers.coerceIn(0, totalAnswers)

    Scaffold(containerColor = Color.White) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF7067FF))
                .padding(paddingValues)
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color(0xFF6D67FF))
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Результаты",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                        .background(Color.White, shape = RoundedCornerShape(35.dp))

                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

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

                    Text(
                        text = "$correctAnswers из $totalAnswers",
                        fontSize = 20.sp,
                        color = Color(0xFFFFB800),
                        fontWeight = FontWeight.Bold
                    )


                    Spacer(modifier = Modifier.height(20.dp))

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
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center

                    )

                    Spacer(modifier = Modifier.height(20.dp))

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
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center

                    )

                    Spacer(modifier = Modifier.height(20.dp))



                }
            }

            items(results) { result ->

                Card_history_result(result_history_models = result)
            }

            item {

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}