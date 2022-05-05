package com.android.alarmy.data.local

import androidx.room.*
import com.android.alarmy.model.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDatabaseDao {
    @Query(value = "SELECT * FROM alarms_tbl")
    fun getAlarms(): Flow<List<Alarm>>

    @Query(value = "SELECT * FROM alarms_tbl WHERE id = :id")
    suspend fun getAlarmsById(id: String): Alarm

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inset(alarm: Alarm)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(alarm: Alarm)

    @Query(value = "DELETE FROM alarms_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)
}