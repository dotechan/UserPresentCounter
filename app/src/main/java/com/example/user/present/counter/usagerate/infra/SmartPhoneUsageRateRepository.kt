package com.example.user.present.counter.usagerate.infra

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SmartPhoneUsageRateRepository(
        private val sharedPref: SharedPreferences) : ISmartPhoneUsageRateRepository {
    companion object {
        @Volatile
        private var INSTANCE: SmartPhoneUsageRateRepository? = null

        fun getRepository(sharedPref: SharedPreferences): SmartPhoneUsageRateRepository = INSTANCE
                ?: synchronized(this) {
                    SmartPhoneUsageRateRepository(sharedPref).also {
                        INSTANCE = it
                    }
                }
    }

    var smartPhoneUsageRate: MutableLiveData<SmartPhoneUsageRate> = MutableLiveData()

    override fun load(): MutableLiveData<SmartPhoneUsageRate> {
        Timber.d("load")

        val userPresentCount = sharedPref.getInt(
                SmartPhoneUsageRateRepository.Key.USAGE_RATE.name,
                SmartPhoneUsageRate.INITIAL_COUNT)

        smartPhoneUsageRate.value = SmartPhoneUsageRate(userPresentCount)

        return smartPhoneUsageRate
    }

    override fun increment() {
        Timber.d("increment")

        val currentUserPresentCount = sharedPref.getInt(
                SmartPhoneUsageRateRepository.Key.USAGE_RATE.name,
                SmartPhoneUsageRate.INITIAL_COUNT)
        Timber.d("current count = $currentUserPresentCount")

        val incrementedUserPresentCount = currentUserPresentCount.inc()
        Timber.d("incremented count = $incrementedUserPresentCount")
        smartPhoneUsageRate.value = SmartPhoneUsageRate(incrementedUserPresentCount)

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            with(sharedPref.edit()) {
                putInt(SmartPhoneUsageRateRepository.Key.USAGE_RATE.name,
                        incrementedUserPresentCount)
                commit()
            }
        }
    }

    override fun reset() {
        Timber.d("reset")

        smartPhoneUsageRate.value = SmartPhoneUsageRate(SmartPhoneUsageRate.INITIAL_COUNT)

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            with(sharedPref.edit()) {
                putInt(SmartPhoneUsageRateRepository.Key.USAGE_RATE.name,
                        SmartPhoneUsageRate.INITIAL_COUNT)
                commit()
            }
        }
    }

    enum class Key {
        USAGE_RATE_FILE,
        USAGE_RATE,
    }
}
