package com.example.dailyquiz2.presentation.History_Quiz

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.dailyquiz2.R
import com.example.dailyquiz2.presentation.Quiz_analysis_screen.Card_analysis
import kotlin.collections.emptyList



@Composable
fun history_Quiz(
    navController: NavController,
    viewModel: HistoryViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val historyList by viewModel.history.collectAsState()
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }


    val deleteHistoryItem: (Int) -> Unit = { index ->
        viewModel.deleteHistory(historyList[index])
        showDialog = false
        selectedIndex.value = null
    }




    Scaffold(containerColor = Color.White) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = PaddingValues())
                .background(Color(0xFF7067FF), shape = RoundedCornerShape(1.dp))
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Text(
                    text = "История",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),

                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(historyList) { index, item ->
                        // Каждая карточка
                        Card_history(
                            historyModels = item,
                            quizIndex = index + 1,
                            isSelected = selectedIndex.value == index,
                            onSelect = {
                                selectedIndex.value = index
                                showDialog = true
                            },
                            onDeleteClick = {
                                selectedIndex.value = index
                                showDialog = true
                            }
                        )
                    }
                }




            }

            LaunchedEffect(showDialog) {
                Log.d("history_Quiz", "ShowDialog is now: $showDialog")
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


@Composable
fun OverlayBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
            .clickable {

            }
    )
}



