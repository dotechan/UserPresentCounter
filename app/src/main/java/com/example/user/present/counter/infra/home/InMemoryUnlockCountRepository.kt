package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.IUnlockCountRepository
import com.example.user.present.counter.domain.home.UnlockCount

class InMemoryUnlockCountRepository : IUnlockCountRepository {
    private var unlockCount = UnlockCount(0)

    override fun load() : UnlockCount {
        return unlockCount
    }

    override fun save(unlockCount: UnlockCount) {
        this.unlockCount = unlockCount
    }
}