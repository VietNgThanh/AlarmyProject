package com.android.alarmy.utils

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.*

object Time {
    fun weekdaysToString(weekdays: List<Boolean>): String {

        if (!weekdays.contains(false)) {
            return "Hằng ngày"
        }

        var result = StringBuilder()
        weekdays.forEachIndexed { index, b ->
            if (b) {
                result.append(boolToDay(index))
                result.append(", ")
            }

        }
        if (result.isNotEmpty()) {
            result = result.deleteAt(result.length - 2)
        }
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

    fun boolToDay(index: Int): String {
        return when (index) {
            0 -> "T.2"
            1 -> "T.3"
            2 -> "T.4"
            3 -> "T.5"
            4 -> "T.6"
            5 -> "T.7"
            6 -> "CN"
            else -> ""
        }
    }
}

