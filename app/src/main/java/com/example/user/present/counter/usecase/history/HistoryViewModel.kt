package com.example.user.present.counter.usecase.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.user.present.counter.data.Injection
import com.example.user.present.counter.domain.history.History
import com.example.user.present.counter.domain.history.IHistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IHistoryRepository

    val historyList: LiveData<List<History>>

    init {
        repository = Injection.provideHistoryRepository(application.applicationContext)
        historyList = repository.getHistoryList()
    }
}