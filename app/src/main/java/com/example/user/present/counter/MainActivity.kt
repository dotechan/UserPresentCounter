package com.example.user.present.counter

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.user.present.counter.MeasurementReceiver
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mMeasurementReceiver: MeasurementReceiver
    private lateinit var mStartButton: View
    private lateinit var mStopButton: View
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStartButton = findViewById(R.id.start_button)
        mStartButton.setOnClickListener(View.OnClickListener {
            Timber.d("onClick: start")
            startMeasurement()
            hideStartButton()
            showStopButton()
        })
        mStopButton = findViewById(R.id.stop_button)
        mStopButton.setOnClickListener(View.OnClickListener {
            Timber.d("onClick: stop")
            stopMeasurement()
            hideStopButton()
            showStartButton()
        })
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            Timber.d("onClick: reset")
            resetUnlockCount()
            updateUnlockCountView()
        }
        registerMeasurementReceiver()
    }

    override fun onStart() {
        Timber.d("onStart")
        super.onStart()
    }

    override fun onNewIntent(intent: Intent) {
        Timber.d("onNewIntent")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()
        updateUnlockCountView()
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.d("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        unregisterMeasurementReceiver()
        super.onDestroy()
    }

    private fun registerMeasurementReceiver() {
        mMeasurementReceiver = MeasurementReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(MeasurementReceiver.ACTION_START_MEASUREMENT)
            addAction(MeasurementReceiver.ACTION_STOP_MEASUREMENT)
        }
        registerReceiver(mMeasurementReceiver, intentFilter)
    }

    private fun unregisterMeasurementReceiver() {
        unregisterReceiver(mMeasurementReceiver)
    }

    private fun startMeasurement() {
        val intent = Intent(applicationContext, MeasurementReceiver::class.java).apply {
            action = MeasurementReceiver.ACTION_START_MEASUREMENT
        }
        sendBroadcast(intent)
    }

    private fun stopMeasurement() {
        val intent = Intent(applicationContext, MeasurementReceiver::class.java).apply {
            action = MeasurementReceiver.ACTION_STOP_MEASUREMENT
        }
        sendBroadcast(intent)
    }

    private fun resetUnlockCount() {
        // TODO: SharedPreferencesへのアクセスを行うクラスに切り出した方がいい
        // TODO: SharedPreferencesの更新後に画面表示の更新処理も行う必要あり
        val sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt(getString(R.string.saved_unlock_count_key),
                    resources.getInteger(R.integer.initial_unlock_count))
            apply()
        }
    }

    private fun updateUnlockCountView() {
        val sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val originalUnlockCount = sharedPreferences.getInt(getString(R.string.saved_unlock_count_key),
                resources.getInteger(R.integer.initial_unlock_count))
        // PINコード解除後の画面表示回数に下線を引く
        val unlockCount = SpannableStringBuilder(originalUnlockCount.toString())
        unlockCount.setSpan(UnderlineSpan(),
                0,
                unlockCount.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val countTextView = findViewById<TextView>(R.id.unlock_count)
        countTextView.text = unlockCount
    }

    private fun showStartButton() {
        mStartButton.visibility = View.VISIBLE
    }

    private fun hideStartButton() {
        mStartButton.visibility = View.GONE
    }

    private fun showStopButton() {
        mStopButton.visibility = View.VISIBLE
    }

    private fun hideStopButton() {
        mStopButton.visibility = View.GONE
    }
}