package com.example.user.present.counter.domain.home

interface IUnlockCountRepository {
    fun load() : UnlockCount

    fun save(unlockCount: UnlockCount)
}