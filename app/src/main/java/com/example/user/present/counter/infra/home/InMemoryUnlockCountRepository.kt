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
                    InMemoryUnlockCountRepository()
                }
    }

    private var unlockCount = UnlockCount(0)

    override fun load(): UnlockCount {
        Timber.d("load unlockCount = ${unlockCount.count}")
        Timber.d("load unlockCount this = $this")
        return unlockCount
    }

    override fun save(unlockCount: UnlockCount) {
        Timber.d("save unlockCount = ${unlockCount.count}")
        Timber.d("save unlockCount this = $this")
        this.unlockCount = unlockCount
    }

    override fun toString(): String {
        return "${unlockCount.count}"
    }
}