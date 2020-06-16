package com.example.user.present.counter.history.infra

import android.content.Context
import androidx.room.Room
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(minSdk = 21, maxSdk = 28)
@RunWith(RobolectricTestRunner::class)
class HistoryRepositoryTest {
    lateinit var repository: HistoryRepository

//    @Before
//    fun setUp() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val historyDao = Room
//                .databaseBuilder(
//                        context,
//                        HistoryDatabase::class.java,
//                        "history_database")
//                .allowMainThreadQueries() // テスト実行中はクエリをAndroidのメインスレッドで実行する
//                .build()
//                .historyDao()
//        repository = HistoryRepository(historyDao)
//    }
}
