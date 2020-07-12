package com.example.user.present.counter.usagerate.domain

class SmartPhoneUsageRate(val userPresentCount: Int) {
    init {
        if (userPresentCount <= INVALID_COUNT)
            // TODO: Kotlinでは例外のthrowは推奨されていないようなのでOption型にした方がいいかも
            // https://kotlinlang.org/docs/tutorials/kotlin-for-py/exceptions.html
            throw IllegalArgumentException("user present count must be positive")
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
    }
}