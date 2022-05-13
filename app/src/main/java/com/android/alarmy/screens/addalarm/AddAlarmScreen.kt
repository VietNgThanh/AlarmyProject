package com.android.alarmy.screens.addalarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.alarmy.components.AlarmActionCard
import com.android.alarmy.components.MyTimePicker
import com.android.alarmy.components.WeekToggleButotn
import com.android.alarmy.model.Alarm
import com.android.alarmy.services.AlarmService
import com.android.alarmy.utils.AppColors
import java.time.LocalTime

@Composable
fun AddAlarmScreen(
    navController: NavController,
    alarm: Alarm,
    onSaveAlarm: (Alarm) -> Unit,
    onDeleteAlarm: (Alarm) -> Unit
) {
    val context = LocalContext.current
    val alarmService = AlarmService(context)
    val scrollState = rememberScrollState()

    val hourState = remember {
        mutableStateOf(alarm.hour)
    }
    val minuteState = remember {
        mutableStateOf(alarm.minute)
    }
    var dayRepeatCheckState = remember {
        mutableStateOf(alarm.days)
    }
    var everyDayCheckBoxState = remember {
        mutableStateOf(!alarm.days.contains(false))
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.AliceBlue)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    modifier = Modifier
                )
            }

            if (scrollState.value != 0) {
                Text(
                    text = "${hourState.value}:${minuteState.value}",
                    style = TextStyle(fontSize = 24.sp)
                )
            }

            Text(
                text = "Xem trước",
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight(.9f)
                .verticalScroll(scrollState)
        ) {
            MyTimePicker(
                time = LocalTime.of(hourState.value, minuteState.value),
                onChangeHour = {
                    hourState.value = it
//                    Log.d("AddAlarmScreen", "Change hour: ${hourState.value}")
                },
                onChangeMinute = {
                    minuteState.value = it
//                    Log.d("AddAlarmScreen", "Change minute: ${minuteState.value}")
                }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(text = "Lặp lại")
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Hằng ngày")
                        Checkbox(checked = everyDayCheckBoxState.value,
                            onCheckedChange = {
                                everyDayCheckBoxState.value = it
                                if (it) {
                                    dayRepeatCheckState.value = List(7) { true }
                                } else {
                                    dayRepeatCheckState.value = List(7) { false }
                                }
                            }
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    WeekToggleButotn(checkedList = dayRepeatCheckState.value,
                        onCheckChange = { index, checked ->
                            val temp = dayRepeatCheckState.value.toMutableList()
                            temp[index] = checked
                            everyDayCheckBoxState.value = !temp.contains(false)
                            dayRepeatCheckState.value = temp.toMutableStateList()
                        })
                }
                Box(
                    contentAlignment = Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(AppColors.AliceBlue),
                ) {
                    Text(
                        text = "Ring in less than 1min.",
                        style = TextStyle(color = AppColors.Mischka),
                        modifier = Modifier
                            .padding(4.dp)

                    )
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .background(AppColors.Solitude)
            )
            AlarmActionCard(
                title = "Cách tắt báo thcứ",
                description = alarm.wakeupMission.name,
                onClick = {})
            AlarmActionCard(title = "Báo lại", description = "Off", onClick = {})
            AlarmActionCard(title = "Nhãn", description = "None", onClick = {})
            AlarmActionCard(title = "Label", description = "None", onClick = {})
            AlarmActionCard(title = "Label", description = "None", onClick = {})
            AlarmActionCard(title = "Label", description = "None", onClick = {})
            OutlinedButton(
                shape = RoundedCornerShape(CornerSize(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .height(40.dp)
                    .fillMaxWidth(),
                onClick = {
                    onDeleteAlarm(alarm)
                    navController.popBackStack()
                }
            ) {
                Text(
                    text = "Xoá",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = AppColors.Mischka,
                        fontSize = 18.sp
                    )
                )
            }
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.TorchRed
            ),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(40.dp)
                .fillMaxWidth(),
            onClick = {
                alarmService.cancelAlarm(alarm)
                alarm.hour = hourState.value
                alarm.minute = minuteState.value
                alarm.state = true
                alarm.days = dayRepeatCheckState.value
//                Log.d("AddAlarmScreen", "AddAlarmScreen: ${alarm.hour} ${alarm.minute}")
//                onSaveAlarm(alarm)
//                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
//
                if (alarm.days.count { it } >= 1) {
                    alarm.nextAlarmInMillis = alarmService.calcNextAlarmInMillis(alarm)
                }
                alarmService.autoSetAlarm(alarm)

                onSaveAlarm(alarm)
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Lưu",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
        }
    }
}

