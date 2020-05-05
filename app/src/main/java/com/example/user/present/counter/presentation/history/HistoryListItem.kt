package com.example.user.present.counter.presentation.history

import com.example.user.present.counter.domain.history.History

sealed class HistoryListItem {
    class HeaderItem(val label: String) : HistoryListItem()
    class HistoryItem(val history: History) : HistoryListItem()
    object BorderItem : HistoryListItem()
}