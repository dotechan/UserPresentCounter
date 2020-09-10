package com.example.user.present.counter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.user.present.counter.databinding.ActivityMainBinding
import com.example.user.present.counter.history.presentation.HistoryFragment
import com.example.user.present.counter.usagerate.presentation.HomeFragment
import com.example.user.present.counter.usagerate.presentation.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        setupBottomNavigationView(navController)
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
        super.onDestroy()
    }

    private fun setupBottomNavigationView(navController: NavController) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.setupWithNavController(navController)
    }
}