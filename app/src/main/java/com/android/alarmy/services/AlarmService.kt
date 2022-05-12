package com.android.alarmy.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.android.alarmy.model.Alarm
import com.android.alarmy.receiver.AlarmReceiver
import com.android.alarmy.utils.Constants
import com.android.alarmy.utils.Time
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    private val intent: Intent = Intent(context, AlarmReceiver::class.java)

    fun setAlarm(alarm: Alarm, repeatable: Boolean = false, nextDay: Int = 0) {
        var day = nextDay
        if (repeatable) {
            intent.setAction(Constants.ACTION_SET_REPETITIVE_ALARM)
                .putExtra("alarm", alarm)
        } else {
            intent.setAction(Constants.ACTION_SET_EXACT_ALARM)
        }
        if (alarm.hour < LocalTime.now().hour || (alarm.hour == LocalTime.now().hour && alarm.minute <= LocalTime.now().minute)) {
            if (day == 0) {
                day++
            }
        }
        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            Time.timestampFromLocalDateTime(
                LocalDateTime.of(
                    LocalDate.now(),
                    LocalTime.of(alarm.hour, alarm.minute, 0, 0)
                )
            ) + java.util.concurrent.TimeUnit.DAYS.toMillis(day.toLong()),
            PendingIntent.getBroadcast(
                context,
                alarm.code,
                intent.putExtra("id", alarm.code),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }

    fun setAlarm(timeInMillis: Long, code: Int) {

        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            PendingIntent.getBroadcast(
                context,
                code,
                intent.putExtra("id", code),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }

    fun cancelAlarm(alarm: Alarm) {
        if (alarm.days.contains(true)) {
            intent.setAction(Constants.ACTION_SET_REPETITIVE_ALARM)
                .putExtra("alarm", alarm)
        } else {
            intent.setAction(Constants.ACTION_SET_EXACT_ALARM)
        }
        val pendingIntent: PendingIntent? =
            PendingIntent.getBroadcast(
                context,
                alarm.code,
                intent.putExtra("id", alarm.code),
                PendingIntent.FLAG_NO_CREATE
            )
        if (pendingIntent != null) {
            alarmManager?.cancel(pendingIntent)
        }
    }

    fun calcNextdayAlarm(list: List<Boolean>, hour: Int, minute: Int): Int {
        var min = 7
        val today = LocalDate.now().dayOfWeek.value - 1
        if (list[today]) {
            if (hour > LocalTime.now().hour || (hour == LocalTime.now().hour && minute > LocalTime.now().minute)) {
                return 0
            }
        }

        if (list.subList(today + 1, 7).contains(true)) {
            return list.subList(today + 1, 7).indexOf(true)
        } else if (list.count { it } != 1){
            return list.indexOf(true) + 7 - today
        }

        return min
    }

    fun autoSetAlarm(alarm: Alarm) {
        if (alarm.days.count { it} == 0) {
            setAlarm(alarm)
        } else {
            setAlarm(
                alarm,
                repeatable = true,
                nextDay = calcNextdayAlarm(
                    alarm.days,
                    alarm.hour,
                    alarm.minute
                )
            )
        }
    }
}