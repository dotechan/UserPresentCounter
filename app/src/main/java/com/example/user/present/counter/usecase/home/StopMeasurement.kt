package com.example.user.present.counter.usecase.home

import android.content.Context
import android.content.Intent
import com.example.user.present.counter.MeasurementReceiver
import timber.log.Timber

class StopMeasurement(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        val intent = Intent(context, MeasurementReceiver::class.java).apply {
            action = MeasurementReceiver.ACTION_STOP_MEASUREMENT
        }
        context.sendBroadcast(intent)
    }
}