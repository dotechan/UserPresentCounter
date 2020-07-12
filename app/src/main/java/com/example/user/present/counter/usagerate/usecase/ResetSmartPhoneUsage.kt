package com.example.user.present.counter.usagerate.usecase

import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository

class ResetSmartPhoneUsage {
    fun execute(repository: ISmartPhoneUsageRateRepository) {
        repository.reset()
    }
}