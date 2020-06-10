package com.example.user.present.counter.domain.home

interface ISmartPhoneUsageRateRepository {
    fun load() : UserPresentCount

    fun save(unlockCount: UserPresentCount)

    fun loadUIData() : Int

    fun reset()
}