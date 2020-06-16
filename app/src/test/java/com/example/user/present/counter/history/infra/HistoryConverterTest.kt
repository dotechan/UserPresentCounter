package com.example.user.present.counter.history.infra

import com.example.user.present.counter.history.domain.Type
import org.junit.Assert.*
import org.junit.Test
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
