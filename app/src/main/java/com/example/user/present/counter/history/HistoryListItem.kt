package com.example.user.present.counter.history

sealed class HistoryListItem {
    class HeaderItem(val label: String) : HistoryListItem()
    class HistoryItem(val history: History) : HistoryListItem()
    object BorderItem : HistoryListItem()
}