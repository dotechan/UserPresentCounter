package com.example.user.present.counter.domain.home

class UserPresentCount(val count: Int) {
    fun increase(): UserPresentCount {
        return UserPresentCount(count.inc())
    }

    fun reset(): UserPresentCount {
        return UserPresentCount(0)
    }
}