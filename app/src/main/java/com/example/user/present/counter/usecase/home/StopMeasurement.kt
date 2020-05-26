package com.example.user.present.counter.usecase.home

import android.content.Context
import android.content.Intent
import com.example.user.present.counter.MeasurementService
import timber.log.Timber

class StopMeasurement(private val context: Context) {
    fun execute() {
        Timber.d("execute")
        val stopServiceIntent = Intent(context.applicationContext, MeasurementService::class.java)
        context.stopService(stopServiceIntent)
    }
}