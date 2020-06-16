package com.example.user.present.counter.history.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "history_table")
data class History(
        @PrimaryKey @ColumnInfo(name = "date") @NonNull val date: Date, // roomで利用するためにgetterが必要
        @ColumnInfo(name = "type") @NonNull val type: Type // roomで利用するためにgetterが必要
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
     * 履歴画面に表示するコンテンツの形式に日付を整形する
     *
     * @return 日付を「時間:分:秒」の形式に整形した文字列
     */
    fun formatListContent(): String {
        return hmsSimpleDateFormat.format(date)
    }

    /**
     * 履歴画面に表示するヘッダーの形式に日付を整形する
     *
     * @return 日付を「MM月dd日」の形式に整形した文字列
     */
    fun formatListHeader(): String {
        return MdSimpleDateFormat.format(date)
    }

    /**
     * 履歴の日付が指定した日付（時間以降は切り捨て）と一致するか文字列に変換してから判定する
     * e.g. 履歴の日付が2020-04-12で指定した日付が2020-04-11の場合はfalseを返す
     *
     * @return 履歴の日付と指定した日付が同じ場合はtrue、異なる場合はfalse
     */
    fun equalsBy(date: Date): Boolean {
        val thisDate = yMdSimpleDateFormat.format(this.date)
        val specifiedDate = yMdSimpleDateFormat.format(date)
        return thisDate == specifiedDate
    }
}