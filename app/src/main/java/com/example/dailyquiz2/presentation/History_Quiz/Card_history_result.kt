package com.example.dailyquiz2.presentation.History_Quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.dailyquiz2.presentation.Quiz_analysis_screen.QuestionResult

@Composable
fun Card_history_result(

    result_history_models: result_history_models,


) {


    val isCorrect = result_history_models.selectedIndex == result_history_models.correctIndex

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {


            Row(

                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Вопрос ${result_history_models.questionIndex + 1} из ${result_history_models.totalQuestions}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFBCB7FF),
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(
                        id = if (isCorrect)
                            R.drawable.true_icon
                        else
                            R.drawable.false_icon
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )



            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = result_history_models.questionText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            result_history_models.options.forEachIndexed { index, optionText ->
                val Selected = index == result_history_models.selectedIndex
                val CorrectSelected = Selected && index == result_history_models.correctIndex

                val borderColor = when {
                    CorrectSelected -> Color(0xFF00AE3A)
                    Selected -> Color(0xFFFF5252)
                    else -> Color.Black
                }

                val iconRes = when {
                    CorrectSelected -> R.drawable.true_icon
                    Selected -> R.drawable.false_icon
                    else -> R.drawable.default_icon
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF3F3F3),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (iconRes != 0) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    Text(
                        text = optionText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = borderColor,
                        maxLines = 3
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}