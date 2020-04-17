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
        // Coroutineの開始方法は二つ
        // launch : 呼び出し元に結果を返さない
        // async : awaitと呼ばれる中断関数でresultを返せるようにする
        // suspend修飾子を付与しておくことで呼び出し元がバックグラウンド実行などの考慮をしなくていい
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            historyRepository.save(history)
        }
    }
}