package com.example.dailyquiz2.presentation.Start_Quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz2.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizInProgressScreen(

    state: QuizUiStateQuizProgress,
    onAnswerSelected: (Int) -> Unit,
    onNext: () -> Unit,
    onNavigateBack: () -> Unit

) {


    val quizList = state.quiz

    val currentQuiz = quizList.getOrNull(state.currentIndex)

    val isLastQuestion = state.currentIndex == quizList.lastIndex



    Scaffold(topBar = {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(paddingValues = PaddingValues(top = 30.dp))
                .background(Color(0xFF6D67FF))
        ) {


            Spacer(modifier = Modifier.padding(top = 20.dp))

            IconButton(


                onClick = onNavigateBack,
                modifier = Modifier.align(Alignment.CenterStart)


            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(48.dp, 28.dp),
                    tint = Color.White

                )
            }

            Image(

                painter = painterResource(id = R.drawable.logo_start_screen),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(230.dp, 60.dp)

            )
        }


    }) { paddingValues ->
        if (quizList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Error")
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
                    .background((Color(0xFF7067FF)), shape = RoundedCornerShape(1.dp))
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally,


                ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(color = Color.White, shape = RoundedCornerShape(40.dp))
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),


                        ) {

                        Spacer(modifier = Modifier.padding(end = 5.dp))


                        Text(
                            text = "Вопрос ${state.currentIndex + 1} из ${state.quiz.size}" ,
                            fontSize = 18.sp,
                            color = Color(0xFFBCB7FF),
                            fontWeight = FontWeight.Bold

                        )

                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    currentQuiz?.let {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center


                        ) {
                            Spacer(modifier = Modifier.height(15.dp))

                            Text(
                                text = currentQuiz.question, fontWeight = FontWeight.Bold,
                                fontSize = 16.sp, textAlign = TextAlign.Center,
                                maxLines = 4

                            )


                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        currentQuiz?.let {quiz ->
                            quiz.answers.forEachIndexed { index, answerText ->

                                val isSelected = state.selectedAnswerIndex == index

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .background(
                                            Color(0xFFF3F3F3),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .border(
                                            1.dp,
                                            color =  if (isSelected) Color(0xFF2B0063) else Color.White,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .clickable { onAnswerSelected(index) }
                                        .padding(18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {


                                    Icon(
                                        painter = if (isSelected)
                                            painterResource(id = R.drawable.selected_icon)
                                        else
                                            painterResource(id = R.drawable.default_icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp),
                                        tint = Color.Unspecified

                                    )



                                    Spacer(modifier = Modifier.width(15.dp))

                                    Text(
                                        text = answerText,
                                        color = if (isSelected) Color(0xFF2B0063) else Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        maxLines = 1
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(25.dp))


                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF7067FF),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable { onNext() },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isLastQuestion)"ЗАВЕРШИТЬ" else "ДАЛЕЕ",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(18.dp)
                        )
                    }




                }
                Text(
                    text = "Вернуться к предыдущим вопросам нельзя",
                    color = Color(0xFF00BCD4),
                    modifier = Modifier.padding(top = 12.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )


            }
        }
    }
}





