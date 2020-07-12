package com.example.user.present.counter.usagerate.usecase

import android.content.Context
import android.content.Intent
import com.example.user.present.counter.usagerate.framework.SmartPhoneUsageMeasurementService
import timber.log.Timber

class StopMeasurement(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        val stopServiceIntent = Intent(context.applicationContext, SmartPhoneUsageMeasurementService::class.java)
        context.stopService(stopServiceIntent)
    }
}