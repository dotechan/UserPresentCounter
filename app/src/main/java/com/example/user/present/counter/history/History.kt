package com.example.user.present.counter.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "history_table")
data class History(
        @PrimaryKey @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "type") val type: Type
) {
    fun formatDate(): String {
        // TODO: RecyclerViewに表示する日付にフォーマットする
        return "3/22 15:48:30"
    }
}