package com.example.user.present.counter.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.user.present.counter.MeasurementReceiver
import com.example.user.present.counter.R
import com.example.user.present.counter.data.Injection
import com.example.user.present.counter.databinding.FragmentHomeBinding
import com.example.user.present.counter.domain.history.Type
import com.example.user.present.counter.usecase.history.RecordHistoryUsecase
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupStartButton()
        setupStopButton()
        setupResetButton()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        updateUnlockCountView()
    }

    private fun startMeasurement() {
        val intent = Intent(requireContext(), MeasurementReceiver::class.java).apply {
            action = MeasurementReceiver.ACTION_START_MEASUREMENT
        }
        requireContext().sendBroadcast(intent)
    }

    private fun stopMeasurement() {
        val intent = Intent(requireContext(), MeasurementReceiver::class.java).apply {
            action = MeasurementReceiver.ACTION_STOP_MEASUREMENT
        }
        requireContext().sendBroadcast(intent)
    }

    private fun resetUnlockCount() {
        // TODO: SharedPreferencesへのアクセスを行うクラスに切り出した方がいい
        // TODO: SharedPreferencesの更新後に画面表示の更新処理も行う必要あり
        val sharedPreferences = requireContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt(getString(com.example.user.present.counter.R.string.saved_unlock_count_key),
                    resources.getInteger(com.example.user.present.counter.R.integer.initial_unlock_count))
            apply()
        }
    }

    private fun updateUnlockCountView() {
        val sharedPreferences = requireContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val originalUnlockCount = sharedPreferences.getInt(getString(R.string.saved_unlock_count_key),
                resources.getInteger(R.integer.initial_unlock_count))
        // PINコード解除後の画面表示回数に下線を引く
        val unlockCount = SpannableStringBuilder(originalUnlockCount.toString())
        unlockCount.setSpan(UnderlineSpan(),
                0,
                unlockCount.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.unlockCount.text = unlockCount
    }

    private fun showStartButton() {
        binding.startButton.visibility = View.VISIBLE
    }

    private fun hideStartButton() {
        binding.startButton.visibility = View.GONE
    }

    private fun showStopButton() {
        binding.stopButton.visibility = View.VISIBLE
    }

    private fun hideStopButton() {
        binding.stopButton.visibility = View.GONE
    }

    private fun recordStartHistory() {
        Timber.d("recordStartHistory")
        val repository = Injection.provideHistoryRepository(requireContext().applicationContext)
        val recordHistoryUsecase = RecordHistoryUsecase(repository)
        recordHistoryUsecase.execute(Type.START)
    }

    private fun recordStopHistory() {
        Timber.d("recordStopHistory")
        val repository = Injection.provideHistoryRepository(requireContext().applicationContext)
        val recordHistoryUsecase = RecordHistoryUsecase(repository)
        recordHistoryUsecase.execute(Type.STOP)
    }

    private fun recordResetHistory() {
        Timber.d("recordResetHistory")
        val repository = Injection.provideHistoryRepository(requireContext().applicationContext)
        val recordHistoryUsecase = RecordHistoryUsecase(repository)
        recordHistoryUsecase.execute(Type.RESET)
    }

    private fun setupResetButton() {
        binding.resetButton.setOnClickListener {
            Timber.d("onClick: reset")
            resetUnlockCount()
            recordResetHistory()
            updateUnlockCountView()
        }
    }

    private fun setupStopButton() {
        binding.stopButton.setOnClickListener {
            Timber.d("onClick: stop")
            stopMeasurement()
            recordStopHistory()
            hideStopButton()
            showStartButton()
        }
    }

    private fun setupStartButton() {
        binding.startButton.setOnClickListener {
            Timber.d("onClick: start")
            startMeasurement()
            recordStartHistory()
            hideStartButton()
            showStopButton()
        }
    }
}