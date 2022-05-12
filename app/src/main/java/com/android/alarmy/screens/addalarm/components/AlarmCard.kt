package com.android.alarmy.screens.addalarm.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.alarmy.model.Alarm
import com.android.alarmy.utils.AppColors
import com.android.alarmy.utils.Time
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun AlarmCard(
    alarm: Alarm,
    onDeleteAlarm: (Alarm) -> Unit,
    onItemClick: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var checked by remember {
        mutableStateOf(alarm.state)
    }
    val time = LocalTime.of(alarm.hour, alarm.minute)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .clip(shape = RoundedCornerShape(CornerSize(10.dp)))
            .clickable {
                onItemClick(alarm.id.toString())
            }
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
                    text = time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
//                    color = contentColor
                )
                Switch(checked = checked,
                    onCheckedChange = {
                        checked = !checked
                        onCheckedChange(it)
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
                        .padding(end = 8.dp),
//                    tint = contentColor
                )
                Text(
                    text = Time.weekdaysToString(alarm.days),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
//                    color = contentColor
                )
                Text(
                    text = "|",
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
//                    color = contentColor
                )
                Text(
                    text = alarm.label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
//                    color = contentColor
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterEnd),
                ) {
                    MyDropdownMenu(
//                        dropdownItemList =,
                        expanded = expanded,
                        onClick = {expanded = true},
                        onDismiss = {expanded = false},
                        onDeleteItem = { onDeleteAlarm(alarm) }

                    )
                }
            }
        }
    }
}

@Composable
fun MyDropdownMenu(
//    dropdownItemList: List<T>,
    expanded: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onDeleteItem: () -> Unit
) {
    Icon(
        Icons.Default.MoreVert,
        contentDescription = "null",
        modifier = Modifier.clickable { onClick() },
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
    ) {
        DropdownMenuItem(onClick = onDeleteItem
        ) {
            Text("Delete")
        }


        DropdownMenuItem(onClick = {}
        ) {
            Text("Duplicate alarm")
        }
    }
}