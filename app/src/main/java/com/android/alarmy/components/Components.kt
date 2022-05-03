package com.android.alarmy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.alarmy.model.Alarm
import com.android.alarmy.utils.AppColors
import java.time.format.DateTimeFormatter

@Preview
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
                Switch(checked = true, onCheckedChange = {})
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
                    text = "Every day",
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