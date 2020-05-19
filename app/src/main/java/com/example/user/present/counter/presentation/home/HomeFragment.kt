package com.example.user.present.counter.presentation.home

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.user.present.counter.R
import com.example.user.present.counter.data.Injection
import com.example.user.present.counter.databinding.FragmentHomeBinding
import com.example.user.present.counter.domain.history.Type
import com.example.user.present.counter.domain.home.IUnlockCountRepository
import com.example.user.present.counter.domain.home.UnlockCount
import com.example.user.present.counter.usecase.history.RecordHistoryUsecase
import com.example.user.present.counter.usecase.home.StartMeasurement
import com.example.user.present.counter.usecase.home.StopMeasurement
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    lateinit var repository: IUnlockCountRepository
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)

        repository = Injection.provideUnlockCountRepository(requireContext())
        // TODO: 計測のライフサイクルを考慮してViewModelからDBに永続化した方がいいのか判断する
        viewModel = ViewModelProvider(activity!!.viewModelStore, HomeViewModelFactory())
                .get(HomeViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupStartButton()
        setupStopButton()
        setupResetButton()

        return binding.root
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()

        updateUnlockCountView()
        updateButton()
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        super.onDestroyView()
    }

    private fun resetUnlockCount() {
        // TODO: SharedPreferencesへのアクセスを行うクラスに切り出した方がいい
        // TODO: SharedPreferencesの更新後に画面表示の更新処理も行う必要あり
        val sharedPreferences = requireContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt(getString(com.example.user.present.counter.R.string.saved_unlock_count_key),
                    UnlockCount(0).count)
            apply()
        }
    }

    private fun updateUnlockCountView() {

        val originalUnlockCount = repository.load().count
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
            StopMeasurement(requireContext()).execute()
            recordStopHistory()
            hideStopButton()
            showStartButton()
            viewModel.isMeasuring = false
        }
    }

    private fun setupStartButton() {
        binding.startButton.setOnClickListener {
            Timber.d("onClick: start")
            StartMeasurement(requireContext()).execute()
            recordStartHistory()
            hideStartButton()
            showStopButton()
            viewModel.isMeasuring = true
        }
    }

    private fun updateButton() {
        Timber.d("isMeasuring = ${viewModel.isMeasuring}")
        if (viewModel.isMeasuring) {
            hideStartButton()
            showStopButton()
        } else {
            hideStopButton()
            showStartButton()
        }
    }
}
