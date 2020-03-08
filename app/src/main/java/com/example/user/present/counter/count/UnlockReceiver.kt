package com.example.user.present.counter.count

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class UnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("onReceive: ${intent.action}")
        val serviceIntent = Intent(context.applicationContext, RecordService::class.java)
        // フォアグラウンドサービスを起動中であればバックグラウンドでもサービスを起動することができる
        context.startService(serviceIntent)
    }

    override fun toString(): String {
        return super.toString()
    }
}