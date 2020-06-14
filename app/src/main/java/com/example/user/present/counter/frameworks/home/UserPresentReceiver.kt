package com.example.user.present.counter.frameworks.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.user.present.counter.usecase.home.RecordSmartPhoneUsage
import timber.log.Timber

class UserPresentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("onReceive: ${intent.action}")
        RecordSmartPhoneUsage(context.applicationContext).execute()
    }

    override fun toString(): String {
        return super.toString()
    }
}