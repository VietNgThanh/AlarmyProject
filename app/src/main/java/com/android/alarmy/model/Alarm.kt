package com.android.alarmy.model

import java.time.DayOfWeek
import java.util.*
import java.time.LocalTime

data class Alarm(
    val id: UUID = UUID.randomUUID(),
    val time: LocalTime = LocalTime.now(),
    val days: Set<DayOfWeek?> = emptySet(),
    val state: Boolean = true,
    val label: String = "Wake up!"
)
