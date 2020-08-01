package com.example.user.present.counter.usagerate.domain

class SmartPhoneUsageRate(var userPresentCount: Int) {
    init {
        require(userPresentCount >= INITIAL_COUNT) {
            "user present count must be positive"
        }

        if (userPresentCount > MAX_COUNT) userPresentCount = MAX_COUNT
    }

    fun record(): SmartPhoneUsageRate {
        return SmartPhoneUsageRate(userPresentCount.inc())
    }

    fun reset(): SmartPhoneUsageRate {
        return SmartPhoneUsageRate(INITIAL_COUNT)
    }

    companion object {
        const val INITIAL_COUNT = 0
        const val INVALID_COUNT = -1
        const val MAX_COUNT = 999
        const val GREATER_MAX_COUNT = 1000
    }
}