package com.android.alarmy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "alarms_tbl")
data class Alarm(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "alarm_hour")
    var hour: Int = LocalDateTime.now().hour,

    @ColumnInfo(name = "alarm_minute")
    var minute: Int = LocalDateTime.now().minute,

    @ColumnInfo(name = "alarm_repetitive_days")
    val days: Set<DayOfWeek?> = emptySet(),

    @ColumnInfo(name = "alarm_state")
    var state: Boolean = true,

    @ColumnInfo(name = "alarm_label")
    var label: String = "Wake up!",

    @ColumnInfo(name = "alarm_ringtone")
    var ringtone: String = "",

    @ColumnInfo(name = "alarm_wakeup_mission")
    var wakeupMission: String = "",
)
