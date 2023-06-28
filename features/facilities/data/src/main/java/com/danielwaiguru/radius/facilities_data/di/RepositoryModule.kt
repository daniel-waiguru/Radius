package com.danielwaiguru.radius.facilities_data.di

import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepository
import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    abstract fun bindFacilitiesRepository(
        facilitiesRepositoryImpl: FacilitiesRepositoryImpl
    ): FacilitiesRepository
}