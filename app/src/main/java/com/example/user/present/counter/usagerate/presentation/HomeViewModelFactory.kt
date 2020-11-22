package com.example.user.present.counter.usagerate.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user.present.counter.usagerate.domain.IMeasurementStateRepository
import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import timber.log.Timber

class HomeViewModelFactory(
        val application: Application,
        val smartPhoneUsageRateRepo: ISmartPhoneUsageRateRepository,
        val measureStateRepo: IMeasurementStateRepository
) : ViewModelProvider.NewInstanceFactory() {

    @SuppressWarnings("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Timber.d("create")
        return HomeViewModel(application, smartPhoneUsageRateRepo, measureStateRepo) as T
    }
}
