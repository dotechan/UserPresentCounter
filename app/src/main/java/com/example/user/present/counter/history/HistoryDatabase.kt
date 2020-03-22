package com.example.user.present.counter.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [History::class], version = 1, exportSchema = false)
@TypeConverters(HistoryConverter::class)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        // Singleton prevents multiple instance of database opening at the same time.
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getDatabase(context: Context): HistoryDatabase = INSTANCE ?: synchronized(this) {
            // synchronizedした後でもう一度INSTANCEのnullチェックが必要になる
            INSTANCE ?: Room.databaseBuilder(
                            context.applicationContext,
                            HistoryDatabase::class.java,
                            "history_database")
                    .build().also { INSTANCE = it }
        }
    }
}
