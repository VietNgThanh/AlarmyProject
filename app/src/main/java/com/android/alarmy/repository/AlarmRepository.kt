package com.android.alarmy.repository

import com.android.alarmy.data.local.AlarmDatabaseDao
import com.android.alarmy.model.Alarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlarmRepository @Inject constructor(private val alarmDatabaseDao: AlarmDatabaseDao) {
    suspend fun addAlarm(alarm: Alarm) = alarmDatabaseDao.inset(alarm)
    suspend fun updateAlarm(alarm: Alarm) = alarmDatabaseDao.update(alarm)
    suspend fun deleteAlarm(alarm: Alarm) = alarmDatabaseDao.deleteAlarm(alarm)
    suspend fun deleteAllAlarms() = alarmDatabaseDao.deleteAll()
//    suspend fun findAlarmById(id: String): Flow<Alarm> =
//        alarmDatabaseDao.getAlarmsById(id).flowOn(Dispatchers.IO).conflate()
//    suspend fun findAlarmById(id: String): Alarm = alarmDatabaseDao.getAlarmsById(id)

    fun getAllAlarms(): Flow<List<Alarm>> =
        alarmDatabaseDao.getAlarms().flowOn(Dispatchers.IO).conflate()
}