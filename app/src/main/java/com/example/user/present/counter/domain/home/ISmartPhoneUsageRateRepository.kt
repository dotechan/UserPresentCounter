package com.example.user.present.counter.domain.home

interface ISmartPhoneUsageRateRepository {
    fun load() : SmartPhoneUsageRate

    fun save(unlockCount: SmartPhoneUsageRate)

    fun loadUIData() : Int

    fun reset()
}