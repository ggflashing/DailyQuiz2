package com.example.dailyquiz2.presentation.History_Quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun historyQuiz(

    navController: NavController,

    historyModels: List<historyModels>,

    onOpenResult: (String) -> Unit,

    onNavigateBack: () -> Unit,

    onDelete: (historyModels) -> Unit,

    navigate_result_histroy: () -> Unit
) {

    val selectedIndex = remember { mutableStateOf<Int?>(null) }

    var showDialog by remember { mutableStateOf(false) }

    val deleteHistoryItem: (Int) -> Unit = { index ->
        val itemToDelete = historyModels[index]
        onDelete(itemToDelete)
        showDialog = false
        selectedIndex.value = null
    }

    Scaffold(containerColor = Color.White) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = PaddingValues())
                .background(Color(0xFF7067FF), shape = RoundedCornerShape(1.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFF6D67FF))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "История",
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



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    itemsIndexed(
                        items = historyModels,
                        key = { _, item -> item.id_history_model }
                    ) { index, item ->

                        CardHistory(
                            historyModels = item,
                            quizIndex = index + 1,
                            onSelect = {
                                onOpenResult(item.id_history_model)
                            },
                            onLongClick = {
                                selectedIndex.value = index
                                showDialog = true
                            }
                        )
                    }


                }

                if (showDialog && selectedIndex.value != null) {
                    val indexToDelete = selectedIndex.value
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = {
                            Text(
                                text = "Удалить квиз?",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold
                            )
                        },
                        text = {
                            Text(
                                text = "Вы уверены, что хотите удалить этот квиз?",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 16.sp
                            )
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    indexToDelete?.let { deleteHistoryItem(it) }
                                    selectedIndex.value = null
                                }
                            ) {
                                Text("Удалить", color = Color(0xFF7067FF))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { showDialog = false }
                            ) {
                                Text("Отмена", color = Color.Gray)
                            }
                        }
                    )
                }
            }
        }
    }
}


