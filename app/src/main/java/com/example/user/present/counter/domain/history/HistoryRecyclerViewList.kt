package com.example.user.present.counter.domain.history

import com.example.user.present.counter.presentation.history.HistoryListItem
import java.util.Date
import kotlin.collections.ArrayList

class HistoryRecyclerViewList(
        private val historyList: List<History>
) : ArrayList<HistoryListItem>() {
    val list = ArrayList<HistoryListItem>()

    init {
        createHistoryRecyclerViewList()
    }

    private fun createHistoryRecyclerViewList() {
        // 履歴の日付が異なればHeaderを挿入する
        // 比較元の日付の初期値は協定世界時（UTC）で1970-01-01T00:00:00としておく
        val previousDate = Date(0)

        historyList.forEach { history ->
            if (history.equalsBy(previousDate).not()) {
                list.add(HistoryListItem.HeaderItem(history.formatListHeader()))
                list.add(HistoryListItem.BorderItem)
            }
            list.add(HistoryListItem.HistoryItem(history))
        }
    }
}