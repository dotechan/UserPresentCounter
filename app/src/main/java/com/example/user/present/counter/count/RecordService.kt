package com.example.user.present.counter.count

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.user.present.counter.R
import com.example.user.present.counter.data.Injection
import com.example.user.present.counter.domain.history.History
import com.example.user.present.counter.domain.history.Type
import com.example.user.present.counter.domain.home.UnlockCount
import com.example.user.present.counter.usecase.history.RecordHistoryUsecase
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.FileDescriptor
import java.io.PrintWriter
import java.util.*

class RecordService : Service() {
    override fun onCreate() {
        Timber.d("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")

        recordUnlockCount()
        recordUnlockHistory()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    override fun dump(fd: FileDescriptor?, writer: PrintWriter?, args: Array<out String>?) {
        super.dump(fd, writer, args)
    }

    private fun recordUnlockCount() {
        Timber.d("recordUnlockCount")
        val repository = Injection.provideUnlockCountRepository(applicationContext)
        val originalUnlockCount = repository.load()
        repository.save(originalUnlockCount.increase())
    }

    private fun recordUnlockHistory() {
        Timber.d("recordUnlockHistory")
        val repository = Injection.provideHistoryRepository(applicationContext)
        val recordHistoryUsecase = RecordHistoryUsecase(repository)
        recordHistoryUsecase.execute(Type.UNLOCK)
    }

    companion object {
        private const val TAG = "RecordService"
    }
}