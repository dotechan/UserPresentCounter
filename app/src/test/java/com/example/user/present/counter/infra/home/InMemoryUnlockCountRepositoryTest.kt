package com.example.user.present.counter.infra.home

import com.example.user.present.counter.domain.home.UnlockCount
import org.junit.Before
import org.junit.Test

import org.junit.Assert.assertEquals

class InMemoryUnlockCountRepositoryTest {
    private lateinit var target: InMemoryUnlockCountRepository

    @Before
    fun setUp() {
        target = InMemoryUnlockCountRepository()
    }

    @Test
    fun load_saveThreeTimes_return3() {
        target.save(UnlockCount(3))
        val actual = target.load().count
        assertEquals(3, actual)
    }
}