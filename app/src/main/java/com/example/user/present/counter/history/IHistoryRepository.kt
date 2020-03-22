package com.example.user.present.counter.history

import androidx.lifecycle.LiveData

interface IHistoryRepository {

    fun getHistoryList(): LiveData<List<History>>

    suspend fun save(history: History)
}