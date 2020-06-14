package com.example.user.present.counter.usecase.home

import android.content.Context
import android.content.Intent
import com.example.user.present.counter.frameworks.home.SmartPhoneUsageMeasurementService
import timber.log.Timber

class StartMeasurement(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        // TODO: WorkerThreadでSmartPhoneUsageMeasurementServiceを実行する
        val startServiceIntent = Intent(context.applicationContext, SmartPhoneUsageMeasurementService::class.java)
        context.startService(startServiceIntent)
    }
}