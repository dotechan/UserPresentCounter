package com.example.user.present.counter.history.usecase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.user.present.counter.di.Injection
import com.example.user.present.counter.history.domain.History
import com.example.user.present.counter.history.domain.IHistoryRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IHistoryRepository

    val historyList: LiveData<List<History>>

    init {
        repository = Injection.provideHistoryRepository(application.applicationContext)
        historyList = repository.getHistoryList()
    }
}