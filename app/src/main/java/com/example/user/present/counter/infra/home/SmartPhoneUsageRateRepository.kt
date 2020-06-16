package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.domain.home.SmartPhoneUsageRate

class SmartPhoneUsageRateRepository : ISmartPhoneUsageRateRepository {
    override fun load() : SmartPhoneUsageRate {
        // TODO("Not yet implemented")
        return SmartPhoneUsageRate(5)
    }

    override fun save(unlockCount: SmartPhoneUsageRate) {
        // TODO("Not yet implemented")
    }

    override fun loadUIData(): Int {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}