package com.danielwaiguru.radius.facilities_data.di

import com.danielwaiguru.radius.facilities_data.api.data_source.RadiusNetworkDataSource
import com.danielwaiguru.radius.facilities_data.api.data_source.RadiusNetworkDataSourceImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkDataSourceModule {
    internal abstract fun bindRadiusNetworkDataSource(
        radiusNetworkDataSourceImpl: RadiusNetworkDataSourceImpl
    ): RadiusNetworkDataSource
}