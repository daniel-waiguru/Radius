package com.danielwaiguru.radius.work.di

import com.danielwaiguru.radius.work.RadiusWorkScheduler
import com.danielwaiguru.radius.work.RadiusWorkSchedulerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RadiusWorkModule {
    @Binds
    @Singleton
    internal abstract fun bindsRadiusWorkScheduler(
        radiusWorkSchedulerImpl: RadiusWorkSchedulerImpl
    ): RadiusWorkScheduler
}