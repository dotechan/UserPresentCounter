package com.example.user.present.counter.usagerate.presentation

import androidx.lifecycle.ViewModel
import timber.log.Timber

class HomeViewModel : ViewModel() {
    var isMeasuring = false

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
    }
}
