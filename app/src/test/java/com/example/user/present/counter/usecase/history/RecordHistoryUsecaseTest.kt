package com.example.user.present.counter.usecase.history

import androidx.lifecycle.LiveData
import com.example.user.present.counter.domain.history.History
import com.example.user.present.counter.domain.history.IHistoryRepository
import com.example.user.present.counter.domain.history.Type
import org.hamcrest.CoreMatchers
import org.junit.Test

import org.junit.Assert.assertTrue
import org.junit.Assert.assertThat
import org.junit.Before

class RecordHistoryUsecaseTest {
    lateinit var repository: MockHistoryRepository

    class MockHistoryRepository : IHistoryRepository {
        var history: History? = null
        var isCalled = false

        override fun getHistoryList(): LiveData<List<History>> {
            TODO("Not yet implemented")
        }

        override suspend fun save(history: History) {
            this.history = history
            isCalled = true
        }
    }

    @Before
    fun setUp() {
        repository = MockHistoryRepository()
    }

    @Test
    fun execute_assertCalled() {
        val target = RecordHistoryUsecase(repository)
        target.execute(Type.UNLOCK)

        // TODO: 非同期（Coroutine）の呼び出しを待ち合わせないといけない
        Thread.sleep(1000)
        val isCalled = repository.isCalled
        // execute()メソッド呼び出し時に意図通りrepositoryのsave()が呼び出されたことを検証する
        assertTrue(isCalled)

        val history = repository.history
        // save()メソッド呼び出し時に引数として渡されたHistoryオブジェクトを検証する
        assertThat(history, CoreMatchers.instanceOf(History::class.java))
    }
}