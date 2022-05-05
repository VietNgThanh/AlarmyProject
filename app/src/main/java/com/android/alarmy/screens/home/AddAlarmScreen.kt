package com.android.alarmy.screens.home

import android.util.Log
import android.widget.Toast
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
import com.android.alarmy.components.DayOfWeekToggleButton
import com.android.alarmy.components.MyTimePicker
import com.android.alarmy.model.Alarm
import com.android.alarmy.services.AlarmService
import com.android.alarmy.utils.AppColors
import com.android.alarmy.utils.Time
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Composable
fun AddAlarmScreen(
    navController: NavController,
    alarm: Alarm,
    onSaveAlarm: (Alarm) -> Unit,
    onDeleteAlarm: (Alarm) -> Unit
) {
    val dows = EnumSet.allOf(DayOfWeek::class.java)
    val scrollState = rememberScrollState()
    val hourState = remember {
        mutableStateOf(alarm.hour)
    }
    val minuteState = remember {
        mutableStateOf(alarm.minute)
    }
    val context = LocalContext.current

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
                text = "Preview",
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
                    Text(text = "Repeat")
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Every day")
                        Checkbox(checked = false, onCheckedChange = {})
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    dows.forEach {
                        DayOfWeekToggleButton(dayOfWeek = it, state = false)
                    }
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
            AlarmActionCard(title = "Mission", description = "Off", onClick = {})
            AlarmActionCard(title = "Snooze", description = "Off", onClick = {})
            AlarmActionCard(title = "Label", description = "None", onClick = {})
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
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            ) {
                Text(
                    text = "Delete",
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
                alarm.hour = hourState.value
                alarm.minute = minuteState.value
                alarm.state = true
                Log.d("AddAlarmScreen", "AddAlarmScreen: ${alarm.hour} ${alarm.minute}")
                onSaveAlarm(alarm)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()

                val alarmService = AlarmService(context)
                alarmService.setExactAlarm(
                    Time.timestampFromLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(alarm.hour, alarm.minute, 0, 0)))
                )
                navController.popBackStack()
            }
        ) {
            Text(
                text = "Save",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
        }
    }
}

