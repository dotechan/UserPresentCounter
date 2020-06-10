package com.example.user.present.counter.domain.history

import com.example.user.present.counter.domain.home.UserPresentCount
import org.junit.Test

import org.junit.Before

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals

/**
 * テストメソッドの命名は[UnitOfWork_StateUnderTest_ExpectedBehavior]とする
 */
class UnlockCountTest {
    lateinit var target: UserPresentCount

    @Before
    fun setUp() {
        target = UserPresentCount(INITIAL_VALUE)
    }

    @Test
    fun increase_increaseOneTime_return1() {
        val actual = target.increase()
        assertThat("unlock countがインクリメントされていません。",
                actual.count, `is`(1))
    }

    @Test
    fun increase_increaseThreeTime_return3() {
        repeat(INCREMENT_THREE_TIMES) {
            target = target.increase()
        }
        val actual = target
        assertThat("unlock countがインクリメントされていません。",
                actual.count, `is`(3))
    }

    @Test
    fun reset_resetAfterIncreaseThreeTime_return0() {
        repeat(INCREMENT_THREE_TIMES) {
            target = target.increase()
        }
        val actual = target.reset()
        assertThat("unlock countが0にリセットされていません。",
                actual.count, `is`(INITIAL_VALUE))
    }

    @Test
    fun getCount_given1_return1() {
        val actual = UserPresentCount(1).count
        assertEquals("unlock countが正しく初期化されていません。",
                actual, 1)
    }

    companion object Const {
        const val INITIAL_VALUE = 0
        const val INCREMENT_THREE_TIMES = 3
    }
}