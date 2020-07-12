package com.example.user.present.counter.usagerate.infra

import android.content.SharedPreferences
import android.content.res.Resources
import com.example.user.present.counter.R
import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate

class SmartPhoneUsageRateRepository(
        private val sharedPref: SharedPreferences,
        private val resources: Resources
) : ISmartPhoneUsageRateRepository {
    override fun load(): SmartPhoneUsageRate {
        val userPresentCount = sharedPref.getInt(
                resources.getString(R.string.smartphone_usage_rate_key),
                SmartPhoneUsageRate.INITIAL_COUNT)

        return SmartPhoneUsageRate(userPresentCount)
    }

    override fun save(unlockCount: SmartPhoneUsageRate) {
        with(sharedPref.edit()) {
            putInt(resources.getString(R.string.smartphone_usage_rate_key),
                    unlockCount.userPresentCount)
            commit()
        }
    }

    override fun reset() {
        with(sharedPref.edit()) {
            putInt(resources.getString(R.string.smartphone_usage_rate_key),
                    SmartPhoneUsageRate.INITIAL_COUNT)
            commit()
        }
    }
}
