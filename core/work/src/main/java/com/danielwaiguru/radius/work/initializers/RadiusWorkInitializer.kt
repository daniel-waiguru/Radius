package com.danielwaiguru.radius.work.initializers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import com.danielwaiguru.radius.work.BuildConfig
import com.danielwaiguru.radius.work.RadiusWorkScheduler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

object RadiusWorkInitializer {
    fun initialize(context: Context) {
        AppInitializer.getInstance(context)
            .initializeComponent(RadiusWorkManagerInitializer::class.java)

    }
}

internal class RadiusWorkManagerInitializer: Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        val workerFactory = getWorkerFactory(context)
        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
            .build()
        WorkManager.initialize(context, config)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf()
    private fun getWorkerFactory(appContext: Context): HiltWorkerFactory {
        val workManagerEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            WorkManagerInitializerEntryPoint::class.java
        )
        return workManagerEntryPoint.hiltWorkerFactory()
    }

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface WorkManagerInitializerEntryPoint {
        fun hiltWorkerFactory(): HiltWorkerFactory
    }
}