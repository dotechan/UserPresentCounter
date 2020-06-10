package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.domain.home.UserPresentCount
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

    private var unlockCount = UserPresentCount(0)

    // TODO: domain層のUnlockCountがpresentation層から操作できてしまうため修正したい
    override fun load(): UserPresentCount {
        Timber.d("load unlockCount = ${unlockCount.count}")
        return unlockCount
    }

    // TODO: domain層のUnlockCountがpresentation層から操作できてしまうため修正したい
    override fun save(unlockCount: UserPresentCount) {
        Timber.d("save unlockCount = ${unlockCount.count}")
        this.unlockCount = unlockCount
    }

    override fun loadUIData(): Int {
        return unlockCount.count
    }

    override fun reset() {
        unlockCount = UserPresentCount(0)
    }

    override fun toString(): String {
        return "${unlockCount.count}"
    }
}
