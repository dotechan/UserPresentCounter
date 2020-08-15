package com.example.user.present.counter.di

import android.content.Context
import com.example.user.present.counter.R
import com.example.user.present.counter.history.infra.HistoryDatabase
import com.example.user.present.counter.history.infra.HistoryRepository
import com.example.user.present.counter.usagerate.infra.InMemorySmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.infra.SmartPhoneUsageRateRepository

// TODO: java -> mockにディレクトリを移動する
class Injection {

    companion object {
        fun provideHistoryRepository(context: Context): HistoryRepository {
            val database = HistoryDatabase.getDatabase(context)
            return HistoryRepository(database.historyDao())
        }

        fun provideInMemorySmartPhoneUsageRateRepository(): InMemorySmartPhoneUsageRateRepository {
            return InMemorySmartPhoneUsageRateRepository.getRepository()
        }

        fun provideSmartPhoneUsageRateRepository(context: Context): SmartPhoneUsageRateRepository {
            val sharedPref = context.getSharedPreferences(
                    SmartPhoneUsageRateRepository.Key.USAGE_RATE_FILE.name, Context.MODE_PRIVATE)

            return SmartPhoneUsageRateRepository.getRepository(sharedPref)
        }
    }
}