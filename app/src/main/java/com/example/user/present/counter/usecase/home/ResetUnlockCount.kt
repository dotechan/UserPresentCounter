package com.example.user.present.counter.usecase.home

import com.example.user.present.counter.domain.home.IUnlockCountRepository

class ResetUnlockCount {
    fun execute(repository: IUnlockCountRepository) {
        repository.reset()
    }
}