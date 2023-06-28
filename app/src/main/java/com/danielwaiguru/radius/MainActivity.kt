package com.danielwaiguru.radius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielwaiguru.radius.work.RadiusWorkScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var radiusWorkScheduler: RadiusWorkScheduler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scheduleDataSync()
    }

    private fun scheduleDataSync() {
        radiusWorkScheduler.scheduleGetFacilities()
    }
}