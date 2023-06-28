package com.danielwaiguru.radius.database.di

import com.danielwaiguru.radius.database.data_source.RadiusLocalDataSource
import com.danielwaiguru.radius.database.data_source.RadiusLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    @Singleton
    internal abstract fun bindsRadiusLocalDataSource(
        radiusLocalDataSourceImpl: RadiusLocalDataSourceImpl
    ): RadiusLocalDataSource
}
