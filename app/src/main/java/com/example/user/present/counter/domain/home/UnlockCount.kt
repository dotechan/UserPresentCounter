package com.example.user.present.counter.domain.home

class UnlockCount(val count: Int) {
    fun increase(): UnlockCount {
        return UnlockCount(count.inc())
    }

    fun reset(): UnlockCount {
        return UnlockCount(0)
    }
}