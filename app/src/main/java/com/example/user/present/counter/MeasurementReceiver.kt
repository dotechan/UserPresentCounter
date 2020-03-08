package com.example.user.present.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class MeasurementReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("onReceive")
        val action = intent.action
        Timber.d("action = $action")
        if (action == ACTION_START_MEASUREMENT) {
            val startServiceIntent = Intent(context.applicationContext, MeasurementService::class.java)
            context.startService(startServiceIntent)
        } else if (action == ACTION_STOP_MEASUREMENT) {
            val stopServiceIntent = Intent(context.applicationContext, MeasurementService::class.java)
            context.stopService(stopServiceIntent)
        }
    }

    override fun toString(): String {
        return super.toString()
    }

    companion object {
        const val ACTION_START_MEASUREMENT = "com.example.user.present.counter.ACTION_START_MEASUREMENT"
        const val ACTION_STOP_MEASUREMENT = "com.example.user.present.counter.ACTION_STOP_MEASUREMENT"
    }
}