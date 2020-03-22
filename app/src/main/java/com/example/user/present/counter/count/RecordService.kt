package com.example.user.present.counter.count

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.user.present.counter.R
import com.example.user.present.counter.history.History
import com.example.user.present.counter.history.Type
import timber.log.Timber
import java.io.FileDescriptor
import java.io.PrintWriter
import java.util.*

class RecordService : Service() {
    override fun onCreate() {
        Timber.d("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")

        recordUnlockCount()
        recordUnlockDate(Date())

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    override fun dump(fd: FileDescriptor?, writer: PrintWriter?, args: Array<out String>?) {
        super.dump(fd, writer, args)
    }

    fun recordUnlockCount() {
        val sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE)
        val originalUnlockCount = sharedPreferences.getInt(
                getString(R.string.saved_unlock_count_key),
                resources.getInteger(R.integer.initial_unlock_count))
        val currentUnlockCount = originalUnlockCount + 1

        with(sharedPreferences.edit()) {
            putInt(getString(R.string.saved_unlock_count_key), currentUnlockCount)
            apply()
        }
    }

    fun recordUnlockDate(date: Date) {
        val type = Type.UNLOCK
        val history = History(date, type)
    }

    companion object {
        private const val TAG = "RecordService"
    }
}