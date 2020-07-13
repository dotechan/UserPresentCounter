package com.example.user.present.counter.usagerate.presentation

import android.app.Application
import android.text.SpannableStringBuilder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import timber.log.Timber

class HomeViewModel(
        application: Application,
        val repository: ISmartPhoneUsageRateRepository
) : AndroidViewModel(application) {
    var isMeasuring = false
    var smartPhoneUsageRate: MutableLiveData<SmartPhoneUsageRate> = repository.load()

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
    }
}
