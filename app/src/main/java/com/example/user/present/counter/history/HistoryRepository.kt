package com.example.user.present.counter.history

import androidx.lifecycle.LiveData

class HistoryRepository(
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