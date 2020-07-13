package com.example.user.present.counter.usagerate.usecase

import android.content.Context
import com.example.user.present.counter.di.Injection
import timber.log.Timber

class IncrementSmartPhoneUsage(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        val repository = Injection.provideSmartPhoneUsageRateRepository(context)
        repository.increment()
    }
}
