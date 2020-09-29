package com.example.user.present.counter.usagerate.infra

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.user.present.counter.usagerate.domain.IMeasureStateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MeasureStateRepository(private val sharedPref: SharedPreferences) : IMeasureStateRepository {
    companion object {
        @Volatile
        private var INSTANCE: MeasureStateRepository? = null

        fun getRepository(sharedPref: SharedPreferences): MeasureStateRepository = INSTANCE
                ?: synchronized(this) {
                    MeasureStateRepository(sharedPref).also {
                        INSTANCE = it
                    }
                }
    }

    var isMeasuring: MutableLiveData<Boolean> = MutableLiveData()

    override fun load(): MutableLiveData<Boolean> {
        Timber.d("load")

        val isMeasuring = sharedPref.getBoolean(
                Key.MEASURE_STATE.name, false)

        this.isMeasuring.value = isMeasuring

        return this.isMeasuring
    }

    override fun start() {
        Timber.d("start")

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            with(sharedPref.edit()) {
                putBoolean(Key.MEASURE_STATE.name, true)
                commit()
            }
        }

        isMeasuring.value = true
    }

    override fun stop() {
        Timber.d("stop")

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            with(sharedPref.edit()) {
                putBoolean(Key.MEASURE_STATE.name, false)
                commit()
            }
        }

        isMeasuring.value = false
    }

    enum class Key {
        MEASURE_STATE_FILE,
        MEASURE_STATE,
    }
}
