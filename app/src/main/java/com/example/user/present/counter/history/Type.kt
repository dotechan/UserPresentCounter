package com.example.user.present.counter.history

import com.example.user.present.counter.R

enum class Type(
        val action: String,
        val resourceId: Int
) {
    START("start", R.drawable.ic_start),
    STOP("stop", R.drawable.ic_stop),
    RESET("reset", R.drawable.ic_reset),
    UNLOCK("unlock", R.drawable.ic_unlock)
}