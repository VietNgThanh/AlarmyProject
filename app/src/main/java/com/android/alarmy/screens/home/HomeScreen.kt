package com.android.alarmy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.alarmy.data.viewmodel.AlarmViewModel
import com.android.alarmy.model.Alarm
import com.android.alarmy.navigation.SubAlarmScreens
import com.android.alarmy.screens.addalarm.components.AlarmCardList
import com.android.alarmy.services.AlarmService
import com.android.alarmy.utils.AppColors

@Composable
fun HomeScreen(
    navController: NavController,
    alarmViewModel: AlarmViewModel,
    onDeleteAlarm: (Alarm) -> Unit
) {
    val context = LocalContext.current
    val alarmList by alarmViewModel.alarmList.collectAsState()
    val alarmService = AlarmService(context)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                color = AppColors.AliceBlue
            )
    ) {
//        val nextAlarm  = alarmViewModel.findNextAlarm()
        Greetings(hour = 23, minute = 59)
        AlarmCardList(
            alarmList = alarmList,
            onCardClick = {
                navController.navigate(
                    SubAlarmScreens.EditAlarmScreen.name + "/$it"
                )
            },
            onCardCheckedChange = { alarm, checked ->
                if (checked) {
                    alarmService.setAlarm(alarm)
                } else {
                    alarmService.cancelAlarm(alarm)
                }
                alarmViewModel.changeTaskChecked(alarm.id.toString(), checked)
            },
            onCardDelete = {
                alarmService.cancelAlarm(it)
                alarmViewModel.deleteAlarm(it)
            },
            modifier = Modifier.padding(bottom = 60.dp)
        )
//        LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
//            val gson = Gson()
//
//            itemsIndexed(alarmList.sortedByDescending { it.state }) { index, alarm ->
//                AlarmCard(
//                    alarm = alarm,
//                    onDeleteAlarm = { onDeleteAlarm(alarm) },
//                    context = context,
//                    onItemClick = {
//                        navController.navigate(
//                            SubAlarmScreens.EditAlarmScreen.name + "/${gson.toJson(alarm)}"
//                        )
//                    },
//                    onCheckedChange = {
//                        alarm.state = it
//                        alarmList = alarmList.sortedByDescending { it.state }
//                        alarmViewModel.updateAlarm(alarm)
//                        if (it) {
//                            alarmService.setAlarm(alarm.hour, alarm.minute, alarm.code)
//                        } else {
//                            alarmService.cancelAlarm(alarm.code)
//                        }
//                    }
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun Greetings(
    hour: Int,
    minute: Int,
) {
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
            text = "Alarm will ring in $hour hr. $minute min.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview
@Composable
fun Pv() {
//    HomeScreen()
}