package com.danielwaiguru.radius.facilities_data.mappers

import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import com.danielwaiguru.radius.database.entities.FacilityWithExclusions
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacility
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacilityExclusion
import com.danielwaiguru.radius.facilities_data.models.data.responses.FacilitiesResponse
import com.danielwaiguru.radius.models.FacilityExclusion

fun NetworkFacility.toFacilityEntity(): FacilityEntity =
    FacilityEntity(
        facilityId = facilityId,
        name = name,
        options = options.map(NetworkFacility.Option::toOptionEntity)
    )
internal fun NetworkFacility.Option.toOptionEntity(): FacilityEntity.OptionEntity =
    FacilityEntity.OptionEntity(icon = icon, id = id, name = name)

fun FacilitiesResponse.toFacilityExclusionEntity(): FacilityExclusionEntity =
    FacilityExclusionEntity(
        exclusions = exclusions.map { exclusions ->
            exclusions.map { exclusion ->
                FacilityExclusionEntity.ExclusionEntity(
                    facilityId = exclusion.facilityId,
                    optionsId = exclusion.optionsId
                )
            }
        }
    )

internal fun FacilityExclusionEntity.toFacilityExclusion(): FacilityExclusion =
    FacilityExclusion(
        id = id,
        exclusions = exclusions.map { exclusions->
            exclusions.map { exclusion ->
                FacilityExclusion.Exclusion(
                    facilityId = exclusion.facilityId,
                    optionsId = exclusion.optionsId
                )
            }
        }
    )
internal fun FacilityEntity.toFacility(): com.danielwaiguru.radius.models.Facility =
    com.danielwaiguru.radius.models.Facility(
        facilityId = facilityId,
        name = name,
        options = options.map(FacilityEntity.OptionEntity::toOption)
    )

internal fun FacilityEntity.OptionEntity.toOption(): com.danielwaiguru.radius.models.Facility.SelectableOption =
    com.danielwaiguru.radius.models.Facility.SelectableOption(icon, id, name)