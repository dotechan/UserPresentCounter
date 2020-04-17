package com.example.user.present.counter.data

import android.content.Context
import com.example.user.present.counter.infra.history.HistoryDatabase
import com.example.user.present.counter.infra.history.HistoryRepository

// TODO: java -> mockにディレクトリを移動する
class Injection {

    companion object {
        fun provideHistoryRepository(context: Context): HistoryRepository {
            val database = HistoryDatabase.getDatabase(context)
            return HistoryRepository(database.historyDao())
        }
    }
}