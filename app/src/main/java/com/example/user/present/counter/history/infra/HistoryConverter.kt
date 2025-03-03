package com.example.user.present.counter.history.infra

import androidx.room.TypeConverter
import com.example.user.present.counter.history.domain.Type
import java.util.*

class HistoryConverter {
    @TypeConverter
    fun gMTmsecToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToGMTmsec(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun typeStrToType(value: String): Type {
        return Type.valueOf(value.toUpperCase(Locale.ROOT))
    }

    @TypeConverter
    fun typeToTypeStr(type: Type): String {
        return type.action
    }
}