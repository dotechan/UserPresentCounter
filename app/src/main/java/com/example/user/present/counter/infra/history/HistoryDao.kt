package com.example.user.present.counter.infra.history

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.user.present.counter.domain.history.History

@Dao
interface HistoryDao {

    @Query("SELECT * from history_table ORDER BY date DESC")
    fun getHistoryList(): LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(history: History)
}