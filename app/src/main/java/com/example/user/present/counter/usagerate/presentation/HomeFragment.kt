package com.example.user.present.counter.usagerate.presentation

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.user.present.counter.di.Injection
import com.example.user.present.counter.databinding.FragmentHomeBinding
import com.example.user.present.counter.history.domain.Type
import com.example.user.present.counter.history.usecase.RecordHistoryUsecase
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import com.example.user.present.counter.usagerate.usecase.ResetSmartPhoneUsage
import com.example.user.present.counter.usagerate.usecase.StartMeasurement
import com.example.user.present.counter.usagerate.usecase.StopMeasurement
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        Timber.d("onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(activity!!.viewModelStore,
                HomeViewModelFactory(activity!!.application,
                        Injection.provideSmartPhoneUsageRateRepository(context!!.applicationContext))
        ).get(HomeViewModel::class.java)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        // TODO: ラムダ式で書けるはず
        val smartPhoneUsageRateObserver = Observer<SmartPhoneUsageRate> { new ->
            Timber.i("update smartphone usage.")
            // PINコード解除後の画面表示回数に下線を引く
            val unlockCount = SpannableStringBuilder(new.userPresentCount.toString())
            unlockCount.setSpan(UnderlineSpan(),
                    0,
                    unlockCount.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.unlockCount.text = unlockCount
        }
        viewModel.smartPhoneUsageRate.observe(viewLifecycleOwner, smartPhoneUsageRateObserver)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()

        updateButton()
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.d("onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d("onDetach")
        super.onDetach()
    }

    private fun resetSmartPhoneUsage() {
        Timber.d("resetSmartPhoneUsage")
        ResetSmartPhoneUsage().execute(Injection.provideSmartPhoneUsageRateRepository(context!!.applicationContext))
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
            resetSmartPhoneUsage()
            recordResetHistory()
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
