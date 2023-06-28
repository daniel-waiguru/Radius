package com.danielwaiguru.radius.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat
import com.danielwaiguru.radius.R
import com.danielwaiguru.radius.databinding.ActivityMainBinding
import com.danielwaiguru.radius.work.RadiusWorkScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var radiusWorkScheduler: RadiusWorkScheduler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        scheduleDataSync()
    }

    private fun setupToolbar() {
        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        WindowInsetsControllerCompat(window, window.decorView.rootView)
            .isAppearanceLightStatusBars = true
    }

    private fun scheduleDataSync() {
        radiusWorkScheduler.scheduleGetFacilities()
    }
}