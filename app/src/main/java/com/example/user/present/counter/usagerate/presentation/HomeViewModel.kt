package com.example.user.present.counter.usagerate.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.user.present.counter.usagerate.domain.IMeasurementStateRepository
import com.example.user.present.counter.usagerate.domain.ISmartPhoneUsageRateRepository
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import timber.log.Timber

class HomeViewModel(
        application: Application,
        val smartPhoneUsageRateRepo: ISmartPhoneUsageRateRepository,
        val measureStateRepo: IMeasurementStateRepository
) : AndroidViewModel(application) {
    // 計測中かどうかの状態を永続化しておかないとActivityが破棄された時に状態を復元できない
    // 永続化にはRoomを使用する。ViewModelは画面回転なら耐えられるが、システムによる終了には耐えられない
    // onSavedInstanceStateは少量のデータであれば利用できるが、ユーザーが明示的に終了した場合に耐えられない
    var isMeasuring: MutableLiveData<Boolean> = measureStateRepo.load()
    var smartPhoneUsageRate: MutableLiveData<SmartPhoneUsageRate> = smartPhoneUsageRateRepo.load()

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
    }

    fun start() {
        Timber.d("start")
        measureStateRepo.start()
    }

    fun stop() {
        Timber.d("stop")
        measureStateRepo.stop()
    }
}
