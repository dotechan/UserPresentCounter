package com.example.user.present.counter.history

import androidx.lifecycle.LiveData

class HistoryRepository(
        private val historyDao: HistoryDao
) : IHistoryRepository {

    private val historyList: LiveData<List<History>> = historyDao.getHistoryList()

    override fun getHistoryList(): LiveData<List<History>> {
        return historyList
    }

    override suspend fun save(history: History) {
        historyDao.save(history)
    }
}