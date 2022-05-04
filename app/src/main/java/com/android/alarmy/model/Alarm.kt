package com.android.alarmy.model

import java.time.DayOfWeek
import java.time.LocalTime
import java.util.*

data class Alarm(
    val id: UUID = UUID.randomUUID(),
    val time: LocalTime = LocalTime.now(),
    val days: Set<DayOfWeek?> = emptySet(),
    var state: Boolean = true,
    val label: String = "Wake up!"
)
