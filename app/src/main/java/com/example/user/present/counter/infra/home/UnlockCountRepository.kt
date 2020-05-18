package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.IUnlockCountRepository
import com.example.user.present.counter.domain.home.UnlockCount

class UnlockCountRepository : IUnlockCountRepository {
    override fun load() : UnlockCount {
        // TODO("Not yet implemented")
        return UnlockCount(5)
    }

    override fun save(unlockCount: UnlockCount) {
        // TODO("Not yet implemented")
    }
}