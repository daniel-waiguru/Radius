package com.danielwaiguru.radius.facilities_domain.di

import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepository
import com.danielwaiguru.radius.facilities_domain.GetFacilitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @ViewModelScoped
    fun providesGetFacilitiesUseCase(
        facilitiesRepository: FacilitiesRepository
    ): GetFacilitiesUseCase = GetFacilitiesUseCase(
        facilitiesRepository
    )
}