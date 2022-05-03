package com.android.alarmy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.alarmy.components.AlarmCard
import com.android.alarmy.model.Alarm
import com.android.alarmy.utils.AppColors

@Composable
fun HomeScreen() {
    val alarmList = listOf<Alarm>(
        Alarm(),
        Alarm(),
        Alarm(),
        Alarm(),
        Alarm(),
        Alarm(),
        Alarm(),
        Alarm(),
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                color = AppColors.AliceBlue
            )
    ) {
        Greetings()
        LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
            items(alarmList) { alarm ->
                AlarmCard(alarm = alarm)
            }
        }
//        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun Greetings() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Next alarm",
            fontWeight = FontWeight.Bold,
            color = Color.Black.copy(0.5f)
        )
        Text(
            text = "Alarm will ring in 10 hr. 9min.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun Pv() {
    HomeScreen()
}