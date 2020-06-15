package com.example.user.present.counter.usecase.history

import android.content.Context
import com.example.user.present.counter.data.Injection
import com.example.user.present.counter.domain.history.Type
import timber.log.Timber

class RecordHistory(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        record()
    }

    private fun record() {
        Timber.d("record")
        val repository = Injection.provideHistoryRepository(context.applicationContext)
        // TODO: AndroidのServiceから移植した名残でusecaseからusecaseを実行しているので修正する
        val recordHistoryUsecase = RecordHistoryUsecase(repository)
        recordHistoryUsecase.execute(Type.UNLOCK)
    }
}
