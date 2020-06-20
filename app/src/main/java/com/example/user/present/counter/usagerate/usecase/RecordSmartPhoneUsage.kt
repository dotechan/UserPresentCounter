package com.example.user.present.counter.usagerate.usecase

import android.content.Context
import com.example.user.present.counter.di.Injection
import timber.log.Timber

class RecordSmartPhoneUsage(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        record()
    }

    private fun record() {
        Timber.d("record")
        val repository = Injection.provideInMemorySmartPhoneUsageRateRepository()
        val originalSmartPhoneUsage = repository.load()
        repository.save(originalSmartPhoneUsage.record())
    }
}