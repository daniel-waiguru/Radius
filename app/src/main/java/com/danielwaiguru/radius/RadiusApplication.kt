package com.danielwaiguru.radius

import android.app.Application
import androidx.hilt.work.HiltWorker
import com.danielwaiguru.radius.work.RadiusWorkScheduler
import com.danielwaiguru.radius.work.initializers.RadiusWorkInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RadiusApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    private fun initWorkManager() {
        RadiusWorkInitializer.initialize(this)
    }
}