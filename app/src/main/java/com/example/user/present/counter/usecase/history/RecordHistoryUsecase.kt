package com.example.user.present.counter.usecase.history

import com.example.user.present.counter.domain.history.History
import com.example.user.present.counter.domain.history.IHistoryRepository
import com.example.user.present.counter.domain.history.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class RecordHistoryUsecase(val historyRepository: IHistoryRepository) {

    fun execute(type: Type) {
        val history = History(Date(), type)
        recordHistory(history)
    }

    private fun recordHistory(history: History) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            historyRepository.save(history)
        }
    }
}