package com.danielwaiguru.radius.facilities_domain.di

import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepository
import com.danielwaiguru.radius.facilities_domain.GetFacilitiesUseCase
import com.danielwaiguru.radius.facilities_domain.GetFacilityExclusionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {
    @Provides
    @ViewModelScoped
    fun providesGetFacilitiesUseCase(
        facilitiesRepository: FacilitiesRepository
    ): GetFacilitiesUseCase = GetFacilitiesUseCase(
        facilitiesRepository
    )

    @Provides
    @ViewModelScoped
    fun providesGetFacilityExclusionsUseCase(
        facilitiesRepository: FacilitiesRepository
    ): GetFacilityExclusionsUseCase = GetFacilityExclusionsUseCase(
        facilitiesRepository
    )
}