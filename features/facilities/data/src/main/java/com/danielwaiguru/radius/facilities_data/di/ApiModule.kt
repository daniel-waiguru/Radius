package com.danielwaiguru.radius.facilities_data.di

import com.danielwaiguru.radius.facilities_data.api.FacilitiesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providesFacilitiesApiService(
        retrofit: Retrofit
    ): FacilitiesApiService = retrofit.create(FacilitiesApiService::class.java)
}