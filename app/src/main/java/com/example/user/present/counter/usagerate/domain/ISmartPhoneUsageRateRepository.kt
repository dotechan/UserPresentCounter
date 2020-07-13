package com.example.user.present.counter.usagerate.domain

import androidx.lifecycle.MutableLiveData

interface ISmartPhoneUsageRateRepository {
    fun load() : MutableLiveData<SmartPhoneUsageRate>

    fun increment()

    fun reset()
}