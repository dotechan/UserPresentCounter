package com.example.user.present.counter.usagerate.usecase

import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ResetSmartPhoneUsageTest {
    private val resetUnlockCount = ResetSmartPhoneUsage()

    @MockK
    private lateinit var repository: ISmartPhoneUsageRateRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { repository.reset() } returns Unit
    }

    @Test
    fun execute_givenRepository_calledResetOneTime() {
        resetUnlockCount.execute(repository)

        verify(exactly = 1) {
            repository.reset()
        }
        confirmVerified(repository)
    }
}