package com.example.user.present.counter.data

import android.content.Context
import com.example.user.present.counter.infra.history.HistoryDatabase
import com.example.user.present.counter.infra.history.HistoryRepository
import com.example.user.present.counter.infra.home.InMemorySmartPhoneUsageRateRepository

// TODO: java -> mockにディレクトリを移動する
class Injection {

    companion object {
        fun provideHistoryRepository(context: Context): HistoryRepository {
            val database = HistoryDatabase.getDatabase(context)
            return HistoryRepository(database.historyDao())
        }

        fun provideUnlockCountRepository(context: Context): InMemorySmartPhoneUsageRateRepository {
            return InMemorySmartPhoneUsageRateRepository.getRepository()
        }
    }
}