package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.domain.home.SmartPhoneUsageRate
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

    override fun loadUIData(): Int {
        return unlockCount.userPresentCount
    }

    override fun reset() {
        unlockCount = SmartPhoneUsageRate(0)
    }

    override fun toString(): String {
        return "${unlockCount.userPresentCount}"
    }
}
