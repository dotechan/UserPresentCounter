package com.example.user.present.counter.usagerate.domain

interface ISmartPhoneUsageRateRepository {
    fun load() : SmartPhoneUsageRate

    fun save(unlockCount: SmartPhoneUsageRate)

    fun loadUIData() : Int

    fun reset()
}