package com.example.user.present.counter.frameworks.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.user.present.counter.domain.home.RecordSmartPhoneUsageService
import timber.log.Timber

class UserPresentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("onReceive: ${intent.action}")
        val serviceIntent = Intent(context.applicationContext, RecordSmartPhoneUsageService::class.java)
        // フォアグラウンドサービスを起動中であればバックグラウンドでもサービスを起動することができる
        context.startService(serviceIntent)
    }

    override fun toString(): String {
        return super.toString()
    }
}