package com.android.alarmy.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.alarmy.data.viewmodel.AlarmViewModel
import com.android.alarmy.model.Alarm
import com.android.alarmy.services.AlarmService
import com.android.alarmy.utils.Constants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.karn.notify.Notify
import java.time.LocalTime


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        myNotification(context)
        val gson = Gson()
        val alarmService = AlarmService(context)
        val stringAlarm = intent.getStringExtra("alarm")
        Log.d("setAlarm", "onReceive: $stringAlarm")
        val alarm: Alarm = gson.fromJson(stringAlarm, Alarm::class.java)

        when (intent.action) {
            Constants.ACTION_SET_EXACT_ALARM -> {

            }
            Constants.ACTION_SET_REPETITIVE_ALARM -> {
                if (alarm != null) {
                    alarmService.setAlarm(alarm)
                    Log.d("setAlarm", "onReceive: setNewAlarm")
                }
            }
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