package com.android.alarmy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.alarmy.model.Alarm
import com.android.alarmy.utils.converter.DayOfWeekSetConverter
import com.android.alarmy.utils.converter.UUIDConverter

@Database(entities = [Alarm::class], version = 2, exportSchema = false)
@TypeConverters(UUIDConverter::class, DayOfWeekSetConverter::class)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDatabaseDao(): AlarmDatabaseDao
}