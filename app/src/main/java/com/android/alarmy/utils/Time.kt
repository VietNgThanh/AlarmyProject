package com.android.alarmy.utils

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.*

object Time {
    fun weekdaysToString(weekdays: Set<DayOfWeek?>): String {
        if (weekdays.isEmpty()) {
            return ""
        }

        if (weekdays.size == 7) {
            return "Every day"
        }

        val weekdaysSorted = weekdaysSort(weekdays)

        var result = StringBuilder()
        weekdaysSorted.forEach {
            result.append(it?.getDisplayName(TextStyle.SHORT, Locale.US))
            result.append(", ")
        }
        result = result.deleteAt(result.length - 2)
        return result.toString()
    }

    fun weekdaysToNarrowString(dayOfWeek: DayOfWeek): String =
        dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.US)

    fun timestampFromLocalDateTime(dateTime: LocalDateTime): Long =
        dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    fun localDateTimeFromTimestamp(timestamp: Long): LocalDateTime? = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()
    )

    private fun weekdaysSort(weekdays: Set<DayOfWeek?>): Set<DayOfWeek?> {
        val result = weekdays.sortedBy { it?.ordinal }
        return result.toSet()
    }

}

