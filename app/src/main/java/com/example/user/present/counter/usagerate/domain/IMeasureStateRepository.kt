package com.example.user.present.counter.usagerate.domain

import androidx.lifecycle.MutableLiveData

interface IMeasureStateRepository {
    fun load(): MutableLiveData<Boolean>

    fun start()

    fun stop()
}