package com.example.user.present.counter.domain.home

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.user.present.counter.data.Injection
import com.example.user.present.counter.domain.history.Type
import com.example.user.present.counter.usecase.history.RecordHistoryUsecase
import timber.log.Timber
import java.io.FileDescriptor
import java.io.PrintWriter

// TODO domain層にAndroid frameworkの固有要素であるServiceを配置するのが微妙かな
// ドメインサービスならいいんだが、Serviceの継承をやめたい
class RecordSmartPhoneUsageService : Service() {
    override fun onCreate() {
        Timber.d("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")

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