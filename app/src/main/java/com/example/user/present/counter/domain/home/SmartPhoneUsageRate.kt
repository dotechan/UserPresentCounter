package com.example.user.present.counter.domain.home

class SmartPhoneUsageRate(val userPresentCount: Int) {
    fun record(): SmartPhoneUsageRate {
        return SmartPhoneUsageRate(userPresentCount.inc())
    }

    fun reset(): SmartPhoneUsageRate {
        return SmartPhoneUsageRate(INITIAL_COUNT)
    }

    companion object {
        const val INITIAL_COUNT = 0
    }
}