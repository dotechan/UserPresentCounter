package com.example.user.present.counter.history

import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import org.junit.Test

import org.junit.Before

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals

/**
 * テストメソッドの命名は[UnitOfWork_StateUnderTest_ExpectedBehavior]とする
 */
class UnlockCountTest {
    lateinit var target: SmartPhoneUsageRate

    @Before
    fun setUp() {
        target = SmartPhoneUsageRate(INITIAL_VALUE)
    }

    @Test
    fun increase_increaseOneTime_return1() {
        val actual = target.record()
        assertThat("unlock countがインクリメントされていません。",
                actual.userPresentCount, `is`(1))
    }

    @Test
    fun increase_increaseThreeTime_return3() {
        repeat(INCREMENT_THREE_TIMES) {
            target = target.record()
        }
        val actual = target
        assertThat("unlock countがインクリメントされていません。",
                actual.userPresentCount, `is`(3))
    }

    @Test
    fun reset_resetAfterIncreaseThreeTime_return0() {
        repeat(INCREMENT_THREE_TIMES) {
            target = target.record()
        }
        val actual = target.reset()
        assertThat("unlock countが0にリセットされていません。",
                actual.userPresentCount, `is`(INITIAL_VALUE))
    }

    @Test
    fun getCount_given1_return1() {
        val actual = SmartPhoneUsageRate(1).userPresentCount
        assertEquals("unlock countが正しく初期化されていません。",
                actual, 1)
    }

    companion object Const {
        const val INITIAL_VALUE = 0
        const val INCREMENT_THREE_TIMES = 3
    }
}