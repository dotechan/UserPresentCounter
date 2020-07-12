package com.example.user.present.counter.history.domain

import androidx.lifecycle.LiveData
import com.example.user.present.counter.history.domain.History

interface IHistoryRepository {

    fun getHistoryList(): LiveData<List<History>>

    suspend fun save(history: History)
}