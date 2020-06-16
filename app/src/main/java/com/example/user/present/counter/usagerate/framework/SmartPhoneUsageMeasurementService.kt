package com.example.user.present.counter.usagerate.framework

import android.app.*
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.user.present.counter.MainActivity
import com.example.user.present.counter.R
import timber.log.Timber

class SmartPhoneUsageMeasurementService : Service() {
    private lateinit var mUnlockReceiver: BroadcastReceiver

    override fun onCreate() {
        Timber.d("onCreate")
        super.onCreate()
        registerUnlockReceiver()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")
        // TODO: Notificationのデザインガイド
        // https://material.io/design/platform-guidance/android-notifications.html#
        createNotificationChannel()
        startForeground(1, createNotification())

        // TODO: Startボタンが連打された時にNotificationを連続生成してメモリリークしないか確認する
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Timber.d("onBind")
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
        unregisterUnlockReceiver()
    }

    private fun registerUnlockReceiver() {
        Timber.d("registerUnlockReceiver")
        mUnlockReceiver = UserPresentReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_USER_PRESENT)
        }
        registerReceiver(mUnlockReceiver, intentFilter)
    }

    private fun unregisterUnlockReceiver() {
        unregisterReceiver(mUnlockReceiver)
    }

    private fun createNotification(): Notification {
        // 標準のアクティビティのPendingIntentを設定する
        // https://developer.android.com/training/notify-user/navigation.html?hl=ja#DirectEntry
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        return NotificationCompat.Builder(this, "temp_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("boot service title")
                .setContentText("boot service content text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val channel = NotificationChannel("temp_id", name, NotificationManager.IMPORTANCE_LOW).apply {
                this.description = description
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}