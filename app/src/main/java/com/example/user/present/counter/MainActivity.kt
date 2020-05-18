package com.example.user.present.counter

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.user.present.counter.databinding.ActivityMainBinding
import com.example.user.present.counter.presentation.history.HistoryFragment
import com.example.user.present.counter.presentation.home.HomeFragment
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
        navigateInitialScreen()
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
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, HomeFragment())
                            .commit()
                }
                R.id.navigation_history -> {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, HistoryFragment())
                            .commit()
                }
                R.id.navigation_graph -> {
                    Toast.makeText(
                            baseContext,
                            R.string.toast_graph_nonimplemented,
                            Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_Setting -> {
                    Toast.makeText(
                            baseContext,
                            R.string.toast_setting_nonimplemented,
                            Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun navigateInitialScreen() {
        binding.bottomNavigation.selectedItemId = R.id.navigation_home
    }
}