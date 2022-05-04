package com.android.alarmy.components

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalTime

@Composable
fun MyTimePicker(
    time: LocalTime,
    onChangeHour: (Int) -> Unit,
    onChangeMinute: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyNumberPicker(
            current = time.hour,
            min = 0,
            max = 23,
            onChangeValue = onChangeHour
        )
        Text(
            text = ":",
            style = TextStyle(fontSize = 22.sp),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        MyNumberPicker(
            current = time.minute,
            min = 0,
            max = 59,
            onChangeValue = onChangeMinute
        )
    }
}

@Composable
fun MyNumberPicker(
    current: Int,
    min: Int,
    max: Int,
    onChangeValue: (Int) -> Unit
) {
    AndroidView(
        factory = { context ->
            NumberPicker(context)
                .apply {
                    setOnValueChangedListener { numberPicker, i, i2 ->
                        onChangeValue(i2)
                    }
                    minValue = min
                    maxValue = max
                    value = current
                }
        }
    )
}