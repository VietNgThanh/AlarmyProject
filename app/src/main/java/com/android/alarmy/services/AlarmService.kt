package com.android.alarmy.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.android.alarmy.model.Alarm
import com.android.alarmy.receiver.AlarmReceiver
import com.android.alarmy.utils.Constants
import com.android.alarmy.utils.Time
import com.google.gson.Gson
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.TimeUnit

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    private val intent: Intent = Intent(context, AlarmReceiver::class.java)
    private val gson = Gson()

    fun setAlarm(alarm: Alarm) {
        val timeInMillis = calcNextAlarmInMillis(alarm)
        if (alarm.days.contains(true)) {
            intent.action = Constants.ACTION_SET_REPETITIVE_ALARM
            Log.d("setAlarm", "setAlarm: ${Constants.ACTION_SET_REPETITIVE_ALARM}")
        } else {
            intent.action = Constants.ACTION_SET_EXACT_ALARM
            Log.d("setAlarm", "setAlarm: ${Constants.ACTION_SET_EXACT_ALARM}")
        }
        Log.d(
            "setAlarm",
            "setAlarm: ${
                LocalTime.of(
                    Time.localDateTimeFromTimestamp(timeInMillis).hour,
                    Time.localDateTimeFromTimestamp(timeInMillis).minute
                )
            } - ${
                LocalDate.of(
                    Time.localDateTimeFromTimestamp(timeInMillis).year,
                    Time.localDateTimeFromTimestamp(timeInMillis).month,
                    Time.localDateTimeFromTimestamp(timeInMillis).dayOfMonth
                )
            }"
        )
        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            PendingIntent.getBroadcast(
                context,
                alarm.code,
                intent.putExtra("alarm", gson.toJson(alarm)),
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
            intent.action = Constants.ACTION_SET_REPETITIVE_ALARM
        } else {
            intent.action = Constants.ACTION_SET_EXACT_ALARM
        }
        Log.d("setAlarm", "cancelAlarm: invoke, ${alarmManager != null}")
        val pendingIntent: PendingIntent? =
            PendingIntent.getBroadcast(
                context,
                alarm.code,
                intent,
                PendingIntent.FLAG_NO_CREATE
            )
        if (pendingIntent != null) {
            alarmManager?.cancel(pendingIntent)
            Log.d("setAlarm", "cancelAlarm: ${alarm.hour}:${alarm.minute}")
        }
    }

    fun calcNextdayAlarm(alarm: Alarm): Int {
        // None repetitive alarm
        if (!alarm.days.contains(true)) {
            return if (alarm.hour > LocalTime.now().hour || (alarm.hour == LocalTime.now().hour && alarm.minute > LocalTime.now().minute)) {
                0
            } else {
                1
            }
        }
        // Repetitive alarm
        else {
            val today = LocalDate.now().dayOfWeek.value - 1
            // Next alarm is today
            if (alarm.days[today]) {
                if (alarm.hour > LocalTime.now().hour || (alarm.hour == LocalTime.now().hour && alarm.minute > LocalTime.now().minute)) {
                    return 0
                }
            }
            if (alarm.days.subList(today + 1, 7).contains(true)) {
                return alarm.days.subList(today + 1, 7).indexOf(true) + 1
            }
            return alarm.days.indexOf(true) + 7 - today
        }
    }

    fun autoSetAlarm(alarm: Alarm) {
        setAlarm(alarm)
    }

    fun calcNextAlarmInMillis(alarm: Alarm): Long {
        val nextDay = calcNextdayAlarm(alarm)
        return Time.timestampFromLocalDateTime(
            LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(alarm.hour, alarm.minute, 0, 0)
            )
        ) + TimeUnit.DAYS.toMillis(nextDay.toLong())
    }

}