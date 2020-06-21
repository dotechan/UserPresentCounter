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
    fun increase_increaseOneTime_return1() {
        val actual = target.record()
        MatcherAssert.assertThat("unlock countがインクリメントされていません。",
                actual.userPresentCount, CoreMatchers.`is`(1))
    }

    @Test
    fun increase_increaseThreeTime_return3() {
        repeat(INCREMENT_THREE_TIMES) {
            target = target.record()
        }
        val actual = target
        MatcherAssert.assertThat("unlock countがインクリメントされていません。",
                actual.userPresentCount, CoreMatchers.`is`(3))
    }

    @Test
    fun reset_resetAfterIncreaseThreeTime_return0() {
        repeat(INCREMENT_THREE_TIMES) {
            target = target.record()
        }
        val actual = target.reset()
        MatcherAssert.assertThat("unlock countが0にリセットされていません。",
                actual.userPresentCount, CoreMatchers.`is`(SmartPhoneUsageRate.INITIAL_COUNT))
    }

    @Test
    fun getCount_given1_return1() {
        val actual = SmartPhoneUsageRate(1).userPresentCount
        assertEquals("unlock countが正しく初期化されていません。",
                actual, 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun instantiate_givenInvalidCount_throwIllegalArgumentException() {
        target = SmartPhoneUsageRate(SmartPhoneUsageRate.INVALID_COUNT)
    }

    companion object Const {
        const val INCREMENT_THREE_TIMES = 3
    }
}