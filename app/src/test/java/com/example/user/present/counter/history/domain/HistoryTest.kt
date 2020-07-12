package com.example.user.present.counter.history.domain

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class HistoryTest {
    lateinit var target: History

    @Before
    fun setUp() {
        target = History(Date(), Type.UNLOCK) // Typeは任意
    }

    @After
    fun tearDown() {
    }

    @Test
    fun formatListContent_returnsFormatString() {
        val actual = target.formatListContent()
        // フォーマットの検証であり、厳密にHH:mm:ssといった数字になっているかまでは検証していない
        assertTrue(actual.matches(Regex("^[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$")))
    }

    @Test
    fun formatListHeader_returnsFormatString() {
        val actual = target.formatListHeader()
        // フォーマットの検証であり、厳密にMM月dd日といった数字になっているかまでは検証していない
        // CJK統合漢字の一覧より、右記の漢字をUnicodeで表すと"月"は6708、"日"は65E5
        assertTrue(actual.matches(Regex("^[0-9]{1,2}[\\u6708][0-9]{1,2}[\\u65E5]$")))
    }

    @Test
    fun equalsBy_givenEqualsDate_returnsTrue() {
        val actual = target.equalsBy(Date())
        assertTrue(actual)
    }

    @Test
    fun equalsBy_givenUnequalsDate_returnsFalse() {
        val today = Date()
        // 1日は86,400,000ミリ秒(24 * 60 * 60 * 1000)
        val tomorrowMilliseconds = today.time + 86400000L
        val actual = target.equalsBy(Date(tomorrowMilliseconds))
        assertFalse(actual)
    }
}