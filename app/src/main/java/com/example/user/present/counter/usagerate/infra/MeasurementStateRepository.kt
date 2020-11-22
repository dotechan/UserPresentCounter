package com.example.user.present.counter.usagerate.infra

import androidx.lifecycle.MutableLiveData
import com.example.user.present.counter.usagerate.domain.IMeasurementStateRepository
import timber.log.Timber

class MeasurementStateRepository : IMeasurementStateRepository {
    companion object {
        @Volatile
        private var INSTANCE: MeasurementStateRepository? = null

        fun getRepository(): MeasurementStateRepository = INSTANCE
                ?: synchronized(this) {
                    MeasurementStateRepository().also {
                        INSTANCE = it
                    }
                }
    }

    var isMeasuring: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun load(): MutableLiveData<Boolean> {
        Timber.d("load")
        return isMeasuring
    }

    override fun start() {
        Timber.d("start")
        isMeasuring.value = true
    }

    override fun stop() {
        Timber.d("stop")
        isMeasuring.value = false
    }
}
