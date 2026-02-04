package com.example.dailyquiz2.presentation.Start_Quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz2.R



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartScreen (

    navController: () -> Unit,
    StartedClick : () -> Unit,

){

    Scaffold(containerColor = Color.White) { paddingValues ->


        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(Color(0xFF7067FF), shape = RoundedCornerShape(1.dp))
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){




            Button(
                onClick = navController,
                modifier = Modifier
                    .padding(top = 50.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors( Color.White),
                contentPadding = PaddingValues(15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "История",
                        color = Color(0xFF7067FF),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(13.dp))

                    Image(

                        painter = painterResource(id = R.drawable.history_icon),
                        modifier = Modifier.size(17.dp),
                        contentDescription = "History Icon"


                    )
                }
            }

            Spacer(Modifier.padding(top = 130.dp))


            Image(
                painter = painterResource(id = R.drawable.logo_start_screen),
                modifier = Modifier
                    .sizeIn(280.dp,63.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null,

                )


            Spacer(Modifier.padding(top = 30.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(40.dp))
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    fontWeight = FontWeight.ExtraBold,
                    text = "Добро пожаловать\nв DailyQuiz",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 30.sp,
                        lineHeight = 36.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF7067FF), shape = RoundedCornerShape(16.dp))
                        .clickable {
                            StartedClick()

                        },
                    horizontalArrangement = Arrangement.Center

                ) {

                    Text("НАЧАТЬ ВИКТОРИНУ",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }




        }


    }

}