package com.example.user.present.counter.usagerate.usecase

import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import timber.log.Timber

class ResetSmartPhoneUsage {
    fun execute(repository: ISmartPhoneUsageRateRepository) {
        Timber.d("execute")
        repository.reset()
    }
}