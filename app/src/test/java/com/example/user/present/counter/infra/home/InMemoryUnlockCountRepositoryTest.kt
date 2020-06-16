package com.example.user.present.counter.infra.home

import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import com.example.user.present.counter.usagerate.infra.InMemorySmartPhoneUsageRateRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.assertEquals

class InMemoryUnlockCountRepositoryTest {
    private lateinit var target: InMemorySmartPhoneUsageRateRepository

    @Before
    fun setUp() {
        target = InMemorySmartPhoneUsageRateRepository()
    }

    @Test
    fun load_saveThreeTimes_return3() {
        target.save(SmartPhoneUsageRate(3))
        val actual = target.load().userPresentCount
        assertEquals(3, actual)
    }
}