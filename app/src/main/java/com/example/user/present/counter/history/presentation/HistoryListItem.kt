package com.example.user.present.counter.history.presentation

import com.example.user.present.counter.history.domain.History

sealed class HistoryListItem {
    class HeaderItem(val label: String) : HistoryListItem()
    class HistoryItem(val history: History) : HistoryListItem()
    object BorderItem : HistoryListItem()
}