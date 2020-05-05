package com.example.user.present.counter.infra.history

import com.example.user.present.counter.domain.history.Type
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class HistoryConverterTest {

    private val date = Date()

    @Test
    fun gMTmsecToDate() {
        assertEquals(date, HistoryConverter().gMTmsecToDate(date.time))
    }

    @Test
    fun dateToGMTmsec() {
        assertEquals(date.time, HistoryConverter().dateToGMTmsec(date))
    }

    @Test
    fun typeStrToType() {
        assertEquals(Type.UNLOCK, HistoryConverter().typeStrToType("unlock"))
    }

    @Test
    fun typeToTypeStr() {
        assertEquals("unlock", HistoryConverter().typeToTypeStr(Type.UNLOCK))
    }
}