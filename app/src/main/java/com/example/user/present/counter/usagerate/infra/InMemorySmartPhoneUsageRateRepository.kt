package com.example.user.present.counter.usagerate.infra

import androidx.lifecycle.MutableLiveData
import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import timber.log.Timber

class InMemorySmartPhoneUsageRateRepository : ISmartPhoneUsageRateRepository {
    companion object {
        @Volatile
        private var INSTANCE: InMemorySmartPhoneUsageRateRepository? = null

        fun getRepository(): InMemorySmartPhoneUsageRateRepository = INSTANCE
                ?: synchronized(this) {
                    InMemorySmartPhoneUsageRateRepository().also {
                        INSTANCE = it
                    }
                }
    }

    private var unlockCount: MutableLiveData<SmartPhoneUsageRate> = MutableLiveData()
    init {
        reset()
    }

    override fun load(): MutableLiveData<SmartPhoneUsageRate> {
        Timber.d("load unlockCount = ${unlockCount.value!!.userPresentCount}")
        return unlockCount
    }

    override fun increment() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        unlockCount.value = SmartPhoneUsageRate(0)
    }

    override fun toString(): String {
        return "${unlockCount.value}"
    }
}
