package com.example.user.present.counter.usagerate.infra

import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import timber.log.Timber

class InMemorySmartPhoneUsageRateRepository : ISmartPhoneUsageRateRepository {
    companion object {
        @Volatile
        private var INSTANCE: InMemorySmartPhoneUsageRateRepository? = null

        fun getRepository(): InMemorySmartPhoneUsageRateRepository = INSTANCE
                ?: synchronized(this) {
                    InMemorySmartPhoneUsageRateRepository().also {
                        INSTANCE = it
                    }
                }
    }

    private var unlockCount = SmartPhoneUsageRate(0)

    override fun load(): SmartPhoneUsageRate {
        Timber.d("load unlockCount = ${unlockCount.userPresentCount}")
        return unlockCount
    }

    override fun save(unlockCount: SmartPhoneUsageRate) {
        Timber.d("save unlockCount = ${unlockCount.userPresentCount}")
        this.unlockCount = unlockCount
    }

    override fun reset() {
        unlockCount = SmartPhoneUsageRate(0)
    }

    override fun toString(): String {
        return "${unlockCount.userPresentCount}"
    }
}
