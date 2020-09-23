package com.example.user.present.counter.history.domain

import com.example.user.present.counter.R

enum class Type(
        val action: String,
        val resourceId: Int,
        val historyTitleId: Int
) {
    START("start", R.drawable.ic_start, R.string.history_item_action_start),
    STOP("stop", R.drawable.ic_stop, R.string.history_item_action_stop),
    RESET("reset", R.drawable.ic_reset, R.string.history_item_action_reset),
    UNLOCK("unlock", R.drawable.ic_unlock, R.string.history_item_action_unlock)
}