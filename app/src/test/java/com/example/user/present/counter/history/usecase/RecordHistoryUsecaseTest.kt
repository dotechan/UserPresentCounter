package com.example.user.present.counter.history.usecase

import com.example.user.present.counter.history.domain.Type
import com.example.user.present.counter.history.infra.HistoryRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

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