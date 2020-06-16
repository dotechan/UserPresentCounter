package com.example.user.present.counter.usagerate.infra

import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class InMemorySmartPhoneUsageRateRepositoryTest {
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