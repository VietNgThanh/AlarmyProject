package com.android.alarmy.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.android.alarmy.receiver.AlarmReceiver
import com.android.alarmy.utils.Constants
import com.android.alarmy.utils.RandomInt

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

    fun setExactAlarm(timeInMillis: Long) {
        setAlarm(
            timeInMillis, getPendingIntent(
                getIntent().apply {
                    action = Constants.ACTION_SET_EXACT_ALARM
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                }
            )
        )
    }

    fun setRepetitiveAlarm(timeInMillis: Long) {
        setAlarm(
            timeInMillis, getPendingIntent(
                getIntent().apply {
                    action = Constants.ACTION_SET_REPETITIVE_ALARM
                    putExtra(Constants.EXTRA_EXACT_ALARM_TIME, timeInMillis)
                }
            )
        )
    }

    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent) {
        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )

    }

    private fun getIntent(): Intent = Intent(context, AlarmReceiver::class.java)

    private fun getPendingIntent(intent: Intent) =
        PendingIntent.getBroadcast(
            context,
            RandomInt.getRandomInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
}