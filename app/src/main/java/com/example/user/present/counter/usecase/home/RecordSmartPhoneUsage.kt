package com.example.user.present.counter.usecase.home

import android.content.Context
import com.example.user.present.counter.data.Injection
import timber.log.Timber

class RecordSmartPhoneUsage(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        record()
    }

    private fun record() {
        Timber.d("record")
        val repository = Injection.provideSmartPhoneUsageRepository(context)
        val originalSmartPhoneUsage = repository.load()
        repository.save(originalSmartPhoneUsage.increase())
    }
}