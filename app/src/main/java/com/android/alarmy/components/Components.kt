package com.android.alarmy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.alarmy.model.Alarm
import com.android.alarmy.utils.AppColors
import com.android.alarmy.utils.Time
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

//@Preview
@Composable
fun AlarmCard(alarm: Alarm = Alarm()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .clip(shape = RoundedCornerShape(CornerSize(10.dp)))
            .clickable { }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = alarm.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Switch(checked = alarm.state,
                    onCheckedChange = {
                        alarm.state = !alarm.state
                    }
                )
            }
            Divider(
                color = AppColors.Solitude,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt, contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = Time.weekdaysToString(alarm.days),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "|",
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                )
                Text(
                    text = alarm.label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterEnd),
                ) {
                    var expanded by remember { mutableStateOf(false) }
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "null",
                        modifier = Modifier.clickable { expanded = true })

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        DropdownMenuItem(onClick = {
                            expanded = false

                        }) {
                            Text("Delete")
                        }

                        DropdownMenuItem(onClick = {
                            expanded = false

                        }) {
                            Text("Preview alarm")
                        }

                        DropdownMenuItem(onClick = {
                            expanded = false

                        }) {
                            Text("Skip next alarm")
                        }

                        DropdownMenuItem(onClick = {
                            expanded = false

                        }) {
                            Text("Duplicate alarm")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayOfWeekToggleButton(dayOfWeek: DayOfWeek, state: Boolean) {
    val checkState = remember {
        mutableStateOf(state)
    }
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (checkState.value) AppColors.DeepSkyBlue else AppColors.Periwinkle
        ),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            checkState.value = !checkState.value
        },
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = Modifier
            .size(42.dp)
    ) {
        Text(
            text = Time.weekdaysToNarrowString(dayOfWeek),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}

@Composable
fun AlarmActionCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(CornerSize(10.dp)))
            .clickable {
                onClick()
            }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 22.dp)

        ) {
            Column {
                Text(
                    text = title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = description,
                    style = TextStyle(
                        color = AppColors.Mischka,
                        fontSize = 16.sp
                    )
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier
            )

        }
    }
}