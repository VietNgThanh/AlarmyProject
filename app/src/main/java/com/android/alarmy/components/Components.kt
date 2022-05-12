package com.android.alarmy.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.alarmy.utils.AppColors
import com.android.alarmy.utils.Time
import java.time.DayOfWeek

@Composable
fun DayOfWeekToggleButton(dayOfWeek: DayOfWeek, checked: Boolean, onCheck: (Boolean) -> Unit) {

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (checked) AppColors.DeepSkyBlue else AppColors.Periwinkle
        ),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            onCheck(!checked)
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
fun WeekToggleButotn(checkedList: List<Boolean>, onCheckChange: (Int, Boolean) -> Unit) {
    (0..6).map {index ->
        DayOfWeekToggleButton(dayOfWeek = DayOfWeek.values()[index], checked = checkedList[index], onCheck = { onCheckChange(index, it) })
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

internal fun showToast(string: String, context: Context) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}