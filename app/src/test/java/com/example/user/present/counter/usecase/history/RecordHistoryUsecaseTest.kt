package com.example.user.present.counter.usecase.history

import com.example.user.present.counter.domain.history.Type
import com.example.user.present.counter.infra.history.HistoryRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Before

class RecordHistoryUsecaseTest {
    @MockK
    lateinit var repository: HistoryRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery { repository.save(allAny()) } returns Unit
    }

    @Test
    @ExperimentalCoroutinesApi
    fun execute_assertCalledOnce_repositorySave() = runBlockingTest {
        val target = RecordHistoryUsecase(repository)
        target.execute(Type.UNLOCK)

        coVerify(exactly = 1) {
            repository.save(allAny())
        }
        confirmVerified(repository)
    }
}