package com.example.user.present.counter.usecase.history

import com.example.user.present.counter.domain.history.History
import com.example.user.present.counter.domain.history.Type
import com.example.user.present.counter.infra.history.HistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*

class RecordHistoryUsecaseTest {
    @Mock
    lateinit var repository: HistoryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun execute_assertCalled() {
        val history = History(Date(), Type.UNLOCK)
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            verify(repository, times(1))
                    .save(ArgumentMatchers.eq(history))
        }
    }
}