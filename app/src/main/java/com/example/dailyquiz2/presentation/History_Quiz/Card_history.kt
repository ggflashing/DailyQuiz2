package com.example.dailyquiz2.presentation.History_Quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailyquiz2.R



@SuppressLint("SuspiciousIndentation")
@Composable
fun Card_history(

    historyModels: history_models,
    quizIndex: Int,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onDeleteClick: () -> Unit
) {



    var isSelected by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }


    val backgroundColor = if (isSelected) Color(0xFF7067FF).copy(alpha = 0.8f) else Color.White









        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable { onSelect() },
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {


            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(26.dp))
                    .padding(10.dp)
                    .height(100.dp)


            ) {

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Quiz", fontSize = 24.sp, color = Color(0xFF2B0063),

                        )

                    Text(
                        text = quizIndex.toString(), fontSize = 24.sp, color = Color(0xFF2B0063),

                        )


                }







                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    repeat(historyModels.total) { index ->
                        Icon(
                            painter = painterResource(
                                if (index < historyModels.stars)
                                    R.drawable.active_star
                                else
                                    R.drawable.inactive_star
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = Color.Unspecified
                        )
                    }


                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = historyModels.monthNumber.toString(),
                        fontSize = 15.sp,
                        color = Color(0xFF2B0063),

                        )

                    Text(
                        text = historyModels.month, fontSize = 15.sp, color = Color(0xFF2B0063),

                        )


                }

                Text(
                    text = historyModels.time,
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .padding(16.dp),
                    fontSize = 15.sp,
                    color = Color.Black
                )




            }





        }




    }








