package com.example.user.present.counter.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "history_table")
data class History(
        @PrimaryKey @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "type") val type: Type
) {
    // FIXME: インスタンス毎にSimpleDateFormatを持つのはメモリの無駄なのでクラス変数に修正する
    @Ignore
    private val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.JAPAN)

    /**
     * 履歴画面に表示する形式に整形する
     *
     * @return 日付を「時間:分:秒」の形式に整形した文字列
     */
    fun formatDate(): String {
        return simpleDateFormat.format(date)
    }
}