package com.example.user.present.counter.usagerate.domain

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SmartPhoneUsageRateTest {
    lateinit var target: SmartPhoneUsageRate

    @Before
    fun setUp() {
        target = SmartPhoneUsageRate(SmartPhoneUsageRate.INITIAL_COUNT)
    }

    @Test
    fun instantiate_givenInitialCount_return0() {
        val actual = SmartPhoneUsageRate(SmartPhoneUsageRate.INITIAL_COUNT)
                .userPresentCount
        assertEquals("unlock countの値は0から999にしてください。",
                actual, 0)
    }

    @Test
    fun instantiate_givenMaxCount_return999() {
        val actual = SmartPhoneUsageRate(SmartPhoneUsageRate.MAX_COUNT)
                .userPresentCount
        assertEquals("unlock countの値は0から999にしてください。",
                actual, 999)
    }

    @Test(expected = IllegalArgumentException::class)
    fun instantiate_givenInvalidCount_throwIllegalArgumentException() {
        target = SmartPhoneUsageRate(SmartPhoneUsageRate.INVALID_COUNT)
    }

    @Test
    fun instantiate_givenAboveMaxCount_return999() {
        val actual = SmartPhoneUsageRate(SmartPhoneUsageRate.ABOVE_MAX_COUNT)
                .userPresentCount
        assertEquals("unlock countが1000に到達したら999に丸めてください。",
                actual, 999)
    }

    @Test
    fun record_recordOneTime_return1() {
        val actual = target.record()
        MatcherAssert.assertThat("unlock countがインクリメントされていません。",
                actual.userPresentCount, CoreMatchers.`is`(1))
    }

    @Test
    fun reset_resetOneTime_return0() {
        val actual = target.reset()
        MatcherAssert.assertThat("unlock countが0にリセットされていません。",
                actual.userPresentCount, CoreMatchers.`is`(SmartPhoneUsageRate.INITIAL_COUNT))
    }

    @Test
    fun greaterThanMaxCount_givenMaxCount_returnTrue() {
        val actual = SmartPhoneUsageRate(SmartPhoneUsageRate.MAX_COUNT)
        assertTrue("unlock countが最大値ではありません。",
                actual.greaterThanMaxCount())
    }

    @Test
    fun greaterThanMaxCount_givenBelowMaxCount_returnFalse() {
        val actual = SmartPhoneUsageRate(SmartPhoneUsageRate.BELOW_MAX_COUNT)
        assertFalse("unlock countが最大値以下ではありません。",
                actual.greaterThanMaxCount())
    }

    @Test
    fun greaterThanMaxCount_givenAboveMaxCount_returnTrue() {
        val actual = SmartPhoneUsageRate(SmartPhoneUsageRate.ABOVE_MAX_COUNT)
        assertTrue("unlock countが最大値以上ではありません。",
                actual.greaterThanMaxCount())
    }
}