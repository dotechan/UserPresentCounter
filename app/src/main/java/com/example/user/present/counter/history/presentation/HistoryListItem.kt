package com.example.user.present.counter.history.presentation

import com.example.user.present.counter.R
import com.example.user.present.counter.history.domain.History

sealed class HistoryListItem(val itemViewType: Int) {
    class HeaderItem(val label: String) : HistoryListItem(VIEW_TYPE_HEADER)
    class HistoryItem(val history: History) : HistoryListItem(VIEW_TYPE_HISTORY)
    object BorderItem : HistoryListItem(VIEW_TYPE_BORDER)

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_HISTORY = 2
        private const val VIEW_TYPE_BORDER = 3

        fun getLayoutRes(viewType: Int): Int {
            return when (viewType) {
                VIEW_TYPE_HEADER -> R.layout.item_header
                VIEW_TYPE_HISTORY -> R.layout.item_history
                VIEW_TYPE_BORDER -> R.layout.item_border
                else -> throw IllegalArgumentException("Unknown viewType $viewType")
            }
        }
    }
}