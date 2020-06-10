package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.domain.home.UserPresentCount

class SmartPhoneUsageRateRepository : ISmartPhoneUsageRateRepository {
    override fun load() : UserPresentCount {
        // TODO("Not yet implemented")
        return UserPresentCount(5)
    }

    override fun save(unlockCount: UserPresentCount) {
        // TODO("Not yet implemented")
    }

    override fun loadUIData(): Int {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}