package com.example.dailyquiz2.presentation.Start_Quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dailyquiz2.R



@Composable
fun ProgressScreen (

   // onNavigateBack: () -> Unit,

   // navController: NavController,

   // navigation_To_analysis_screen: () ->  Unit,

  //  failed: () -> Unit


){
    Scaffold (containerColor = Color.White) {paddingValues ->

        Column (
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(Color(0xFF7067FF), shape = RoundedCornerShape(1.dp))
                .padding(30.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_start_screen),
                    modifier = Modifier
                        .sizeIn(280.dp,63.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,

                )

            Spacer(modifier = Modifier.height(50.dp))

                CircularProgressIndicator()
            }

        }

    }

