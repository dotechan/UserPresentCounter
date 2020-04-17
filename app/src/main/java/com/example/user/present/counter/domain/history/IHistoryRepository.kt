package com.example.user.present.counter.domain.history

import androidx.lifecycle.LiveData
import com.example.user.present.counter.domain.history.History

interface IHistoryRepository {

    fun getHistoryList(): LiveData<List<History>>

    suspend fun save(history: History)
}