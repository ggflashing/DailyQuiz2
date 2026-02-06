package com.example.dailyquiz2.presentation.History_Quiz

import android.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.dailyquiz2.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun historyQuiz(

    navController: NavController,

    historyModels: List<historyModels>,

    onOpenResult: (String) -> Unit,

    onNavigateBack: () -> Unit,

    onDelete: (historyModels) -> Unit,

    navigateResultHistroy: () -> Unit
) {

    val systemUi = rememberSystemUiController()

    var MenuId by remember { mutableStateOf<String?>(null) }

    var dim by remember { mutableStateOf(false) }

    var selectedShadow by remember {mutableStateOf(false)}


    val selectedIndex = remember { mutableStateOf<Int?>(null) }

    var showDialog by remember { mutableStateOf(false) }

    val deleteHistoryItem: (Int) -> Unit = { index ->
        val itemToDelete = historyModels[index]
        onDelete(itemToDelete)
        showDialog = true
        selectedIndex.value = null
    }

    LaunchedEffect(dim,showDialog) {
        if (dim || showDialog) {
            systemUi.setStatusBarColor(Color.Black.copy(alpha = 0.0f), )
            systemUi.setNavigationBarColor(Color.Black.copy(alpha = 0.0f))
        } else {
            systemUi.setStatusBarColor(Color(0xFF7067FF), )
            systemUi.setNavigationBarColor(Color(0xFF7067FF))
        }
    }

    LaunchedEffect(showDialog) {
        if (showDialog) {
            systemUi.setStatusBarColor(Color.Black.copy(alpha = 0.0f), )
            systemUi.setNavigationBarColor(Color.Black.copy(alpha = 0.0f))
        } else {
            systemUi.setStatusBarColor(Color(0xFF7067FF), )
            systemUi.setNavigationBarColor(Color(0xFF7067FF))
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
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
                if (historyModels.isEmpty()) {

                    Spacer(modifier = Modifier.height(30.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(35.dp))
                            .padding(paddingValues = PaddingValues(20.dp))
                            .padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(text = "Вы еще не проходили \n ни одной викторины", fontSize = 24.sp)

                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFF7067FF),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clickable {
                                    onNavigateBack()

                                },
                            horizontalArrangement = Arrangement.Center

                        ) {

                            Text(
                                "НАЧАТЬ ВИКТОРИНУ",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                    }

                } else {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .background(Color(0xFF7067FF))
                    ) {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            itemsIndexed(
                                historyModels,
                                key = { _, item -> item.id_history_model }
                            ) { index, item ->


                                Box(modifier = Modifier.background(Color(0xFF7067FF))) {
                                    CardHistory(
                                        historyModels = item,

                                        quizIndex = index + 1,
                                        isSelected = selectedIndex.value == index,
                                        onSelect = { onOpenResult(item.id_history_model) },
                                        onLongClick = {
                                            selectedIndex.value = index
                                            MenuId = item.id_history_model
                                            dim = true
                                            selectedShadow = true
                                        },

                                        )

                                    DropdownMenu(

                                        shape = RoundedCornerShape(18),
                                        containerColor = Color.White,
                                        expanded = MenuId == item.id_history_model,
                                        onDismissRequest = {
                                            MenuId = null
                                            dim = false
                                        },
                                        offset = DpOffset(x = 100.dp, y = (-200).dp)

                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Удалить") },
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(Color.White),
                                            contentPadding = PaddingValues(
                                                end = 150.dp,
                                                start = 10.dp
                                            ),
                                            leadingIcon = {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.trash_icon),
                                                    null, modifier = Modifier.size(23.dp)
                                                )
                                            },
                                            onClick = {
                                                showDialog = true
                                                deleteHistoryItem(index)
                                                MenuId = null
                                                dim = false
                                            }
                                        )
                                    }

                                }
                            }

                        }

                    }
                }
            }

            if (historyModels.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.logo_start_screen),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 70.dp)
                        .sizeIn(180.dp, 40.dp)
                )
            }

        }

        if (showDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.45f))
                    .padding(paddingValues = PaddingValues(22.dp))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { showDialog = false },
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(33.dp))
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Попытка Удалена",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Вы можете пройти викторину снова,\nкогда будете готовы.",
                        textAlign = TextAlign.Center,
                        fontSize = 17.sp,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Закрыть", color = Color(0xFF7067FF), fontSize = 17.sp)
                        }
                    }
                }
            }
        }

        if (dim) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.45f))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        dim = false
                        MenuId = null
                    }
            )
        }
    }

}