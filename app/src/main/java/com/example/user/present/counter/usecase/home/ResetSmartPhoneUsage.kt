package com.example.user.present.counter.usecase.home

import com.example.user.present.counter.domain.home.ISmartPhoneUsageRateRepository

class ResetSmartPhoneUsage {
    fun execute(repository: ISmartPhoneUsageRateRepository) {
        repository.reset()
    }
}