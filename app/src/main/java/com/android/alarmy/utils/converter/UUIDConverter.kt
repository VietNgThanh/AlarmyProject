package com.android.alarmy.utils.converter

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun stringFromUUID(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun uuidFromString(string: String): UUID = UUID.fromString(string)
}