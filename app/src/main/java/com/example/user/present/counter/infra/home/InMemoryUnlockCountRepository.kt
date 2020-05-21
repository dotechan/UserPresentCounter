package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.IUnlockCountRepository
import com.example.user.present.counter.domain.home.UnlockCount
import timber.log.Timber

class InMemoryUnlockCountRepository : IUnlockCountRepository {
    companion object {
        @Volatile
        private var INSTANCE: InMemoryUnlockCountRepository? = null

        fun getRepository(): InMemoryUnlockCountRepository = INSTANCE
                ?: synchronized(this) {
                    InMemoryUnlockCountRepository().also {
                        INSTANCE = it
                    }
                }
    }

    private var unlockCount = UnlockCount(0)

    // TODO: domain層のUnlockCountがpresentation層から操作できてしまうため修正したい
    override fun load(): UnlockCount {
        Timber.d("load unlockCount = ${unlockCount.count}")
        return unlockCount
    }

    // TODO: domain層のUnlockCountがpresentation層から操作できてしまうため修正したい
    override fun save(unlockCount: UnlockCount) {
        Timber.d("save unlockCount = ${unlockCount.count}")
        this.unlockCount = unlockCount
    }

    override fun loadUIData(): Int {
        return unlockCount.count
    }

    override fun reset() {
        unlockCount = UnlockCount(0)
    }

    override fun toString(): String {
        return "${unlockCount.count}"
    }
}
