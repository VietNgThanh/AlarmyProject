package com.android.alarmy.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.android.alarmy.utils.Constants
import io.karn.notify.Notify
import java.time.LocalTime


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Constants.ACTION_SET_EXACT_ALARM -> myNotification(context)
        }
    }

    private fun myNotification(context: Context) {
        Notify
            .with(context = context)
            .content {
                title = "Alarmy Notification"
                text = "Ring at ${LocalTime.now()}"
            }
            .show()
//        val notificationManager = NotificationManagerCompat.from(context)

    }
}