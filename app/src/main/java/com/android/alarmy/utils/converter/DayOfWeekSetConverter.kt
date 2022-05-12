package com.android.alarmy.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DayOfWeekSetConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringFromDayOfWeekSet(dayOfWeekSet: List<Boolean>): String {
        val gson = Gson()
        return gson.toJson(dayOfWeekSet)
    }

    @TypeConverter
    fun dayOfWeekSetFromString(string: String): List<Boolean> {
        val dayOfWeekSetType: Type = object : TypeToken<List<Boolean>>() {}.type
        return gson.fromJson(string, dayOfWeekSetType)
    }
}