package com.example.user.present.counter.usecase.home

import android.content.Context
import android.content.Intent
import com.example.user.present.counter.domain.home.RecordSmartPhoneUsageService
import timber.log.Timber

class RecordSmartPhoneUsage(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        // TODO: WorkerThreadでRecordSmartPhoneUsageServiceを実行する
        val serviceIntent = Intent(context.applicationContext, RecordSmartPhoneUsageService::class.java)
        // フォアグラウンドサービスを起動中であればバックグラウンドでもサービスを起動することができる
        context.startService(serviceIntent)
    }
}