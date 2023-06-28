package com.danielwaiguru.radius.facilities_data.mappers

import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacility
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacilityExclusion

fun NetworkFacility.toFacilityEntity(): FacilityEntity =
    FacilityEntity(
        facilityId = facilityId,
        name = name,
        options = options.map(NetworkFacility.Option::toOptionEntity)
    )
internal fun NetworkFacility.Option.toOptionEntity(): FacilityEntity.OptionEntity =
    FacilityEntity.OptionEntity(icon = icon, id = id, name = name)

fun NetworkFacilityExclusion.toFacilityExclusionEntity(): FacilityExclusionEntity =
    FacilityExclusionEntity(
        facilityId = facilityId,
        optionsId = optionsId
    )