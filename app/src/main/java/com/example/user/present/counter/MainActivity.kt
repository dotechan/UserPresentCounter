package com.example.user.present.counter

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.user.present.counter.databinding.ActivityMainBinding
import com.example.user.present.counter.presentation.history.HistoryFragment
import com.example.user.present.counter.home.HomeFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mMeasurementReceiver: MeasurementReceiver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()
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

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()

        tempHistoryButton()
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

    private fun setupBottomNavigationView() {
    }

    // TODO: BottomNavigationViewから遷移させる方法をマスターするまでの仮のボタン
    private fun tempHistoryButton() {
        binding.historyButton.setOnClickListener {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, HistoryFragment())
                    .commit()
        }
    }
}