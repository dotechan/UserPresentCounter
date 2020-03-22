package com.example.user.present.counter.history

import androidx.room.TypeConverter
import java.util.*

class HistoryConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromAction(value: String): Type {
        return Type.valueOf(value)
    }

    @TypeConverter
    fun typeToAction(type: Type): String {
        return type.action
    }
}