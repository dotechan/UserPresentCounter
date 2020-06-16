package com.example.user.present.counter.history.infra

import androidx.lifecycle.LiveData
import com.example.user.present.counter.history.domain.History
import com.example.user.present.counter.history.domain.IHistoryRepository
import com.example.user.present.counter.history.infra.HistoryDao

// MockitoでMockするために継承できるようにopen修飾子を付与している
open class HistoryRepository(
        private val historyDao: HistoryDao
) : IHistoryRepository {

    private val historyList: LiveData<List<History>> = historyDao.getHistoryList()

    override fun getHistoryList(): LiveData<List<History>> {
        return historyList
    }

    // suspendはKotlinに関数をバックグラウンドで実行するようにという指示ではない
    // ディスクの読み書き、ネットワークオペレーション、CPU負荷の高いオペレーションなどメインセーフティが必要な場合、
    // suspend関数内部からwithContext()を使用する必要がある
    override suspend fun save(history: History) {
        historyDao.save(history)
    }
}