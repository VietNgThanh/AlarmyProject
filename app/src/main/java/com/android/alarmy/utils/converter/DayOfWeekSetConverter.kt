package com.android.alarmy.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.DayOfWeek

class DayOfWeekSetConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringFromDayOfWeekSet(dayOfWeekSet: Set<DayOfWeek?>): String {
        val gson = Gson()
        return gson.toJson(dayOfWeekSet)
    }

    @TypeConverter
    fun dayOfWeekSetFromString(string: String): Set<DayOfWeek?> {
        val dayOfWeekSetType: Type = object : TypeToken<Set<DayOfWeek?>>() {}.type
        return gson.fromJson(string, dayOfWeekSetType)
    }
}