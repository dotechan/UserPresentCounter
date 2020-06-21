package com.example.user.present.counter.usagerate.infra

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.user.present.counter.R
import com.example.user.present.counter.usagerate.domain.SmartPhoneUsageRate
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Android Frameworkを利用した場合のUnit Testの書き方がわからないため保留
// @RunWith(RobolectricTestRunner::class)
// @Config(sdk = [Build.VERSION_CODES.P])
class SmartPhoneUsageRateRepositoryTest {
//    private lateinit var target: SmartPhoneUsageRateRepository
//    private val context = ApplicationProvider.getApplicationContext<Context>()
//    private lateinit var sharedPref: SharedPreferences
//
//    @Before
//    fun setUp() {
//        sharedPref = context.getSharedPreferences(
//                context.getString(R.string.smartphone_usage_rate_file_for_unit_test_key),
//                Context.MODE_PRIVATE
//        )
//        target = SmartPhoneUsageRateRepository(sharedPref, context.resources)
//    }
//
//    @Test
//    fun load() {
//
//    }
//
//    @Test
//    fun save() {
//    }
//
//    @Test
//    fun reset() {
//        target.reset()
//        val userPresentCount = sharedPref.getInt(
//                context.getString(R.string.smartphone_usage_rate_for_unit_test_key),
//                SmartPhoneUsageRate.INVALID_COUNT
//        )
//
//        assertEquals(SmartPhoneUsageRate.INITIAL_COUNT, userPresentCount)
//    }
}