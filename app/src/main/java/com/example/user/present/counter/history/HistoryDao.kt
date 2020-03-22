package com.example.user.present.counter.history

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * from history_table ORDER BY date ASC")
    fun getHistoryList(): LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(history: History)
}