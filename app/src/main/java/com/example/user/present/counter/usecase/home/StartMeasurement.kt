package com.example.user.present.counter.usecase.home

import android.content.Context
import android.content.Intent
import com.example.user.present.counter.MeasurementReceiver
import timber.log.Timber

class StartMeasurement(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        val intent = Intent(context, MeasurementReceiver::class.java).apply {
            action = MeasurementReceiver.ACTION_START_MEASUREMENT
        }
        context.sendBroadcast(intent)
    }
}