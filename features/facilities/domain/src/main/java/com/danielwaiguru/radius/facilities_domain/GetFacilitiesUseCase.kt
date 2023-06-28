package com.danielwaiguru.radius.facilities_domain

import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepository
import com.danielwaiguru.radius.models.Facility
import com.danielwaiguru.radius.models.FacilityExclusion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetFacilitiesUseCase(
    private val facilitiesRepository: FacilitiesRepository
) {
    operator fun invoke(): Flow<List<Facility>> = facilitiesRepository.getFacilities()
}

class GetFacilityExclusionsUseCase(
    private val facilitiesRepository: FacilitiesRepository
) {
    operator fun invoke(): Flow<FacilityExclusion> =
        facilitiesRepository.getFacilityExclusions()
}