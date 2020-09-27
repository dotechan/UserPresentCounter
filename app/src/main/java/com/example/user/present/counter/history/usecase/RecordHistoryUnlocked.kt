package com.example.user.present.counter.history.usecase

import android.content.Context
import com.example.user.present.counter.di.Injection
import com.example.user.present.counter.history.domain.Type
import timber.log.Timber

class RecordHistoryUnlocked(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        record()
    }

    private fun record() {
        Timber.d("record")
        val repository = Injection.provideHistoryRepository(context.applicationContext)
        val recordHistoryUsecase = RecordHistoryUsecase(repository)
        recordHistoryUsecase.execute(Type.UNLOCK)
    }
}
