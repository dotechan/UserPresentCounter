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
    // javaで言うところのクラス変数はkotlinの言語仕様にないため、top-levelプロパティとして宣言している
    @Ignore
    // 履歴画面に表示する時間の形式に整形するフォーマッター
    private val hmsSimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.JAPAN)

    @Ignore
    // 履歴画面に表示する時間の形式に整形するフォーマッター
    private val MdSimpleDateFormat = SimpleDateFormat("MM月dd日", Locale.JAPAN)

    @Ignore
    // 履歴画面に日付ヘッダーを挿入するかの判定に利用するフォーマッター
    private val yMdSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)

    /**
     * 履歴画面に表示する形式に整形するMdSimpleDateFormat
     *
     * @return 日付を「時間:分:秒」の形式に整形した文字列
     */
    fun formatHHmmss(): String {
        return hmsSimpleDateFormat.format(date)
    }

    /**
     * 履歴画面に表示するヘッダーの形式に整形する
     *
     * @return 日付を「xx月xx日」の形式に整形した文字列
     */
    fun formatMMdd(): String {
        return MdSimpleDateFormat.format(date)
    }

    /**
     * 履歴の日付が指定した日付と一致するか判定する
     * e.g. 履歴の日付が2020-04-12で指定した日付が2020-04-11の場合はfalseを返す
     *
     * @return 履歴の日付と指定した日付が同じ場合はtrue、異なる場合はfalse
     */
    fun equalsByDate(date: Date): Boolean {
        val thisDate = yMdSimpleDateFormat.format(this.date)
        val specifiedDate = yMdSimpleDateFormat.format(date)
        return thisDate == specifiedDate
    }
}