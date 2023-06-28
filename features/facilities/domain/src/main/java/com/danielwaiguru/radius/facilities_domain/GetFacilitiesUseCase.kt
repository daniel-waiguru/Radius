package com.danielwaiguru.radius.facilities_domain

import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepository
import com.danielwaiguru.radius.models.Facility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetFacilitiesUseCase(
    private val facilitiesRepository: FacilitiesRepository
) {
    operator fun invoke(): Flow<List<Facility>> = facilitiesRepository.getFacilities()
        .combine(facilitiesRepository.getFacilityExclusions()) { facilities, exclusions ->
            facilities.map { facility ->
                if(exclusions.map { it.facilityId }.contains(facility.facilityId)) {
                    facility.copy(
                        options = facility.options.map { option ->
                            option.copy(
                                isSelectable = exclusions.find { exclusion ->
                                    exclusion.optionsId == option.id
                                } == null
                            )
                        }
                    )
                } else {
                    facility
                }
            }
        }
}