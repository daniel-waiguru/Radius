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

internal fun FacilityExclusionEntity.toFacilityExclusion(): com.danielwaiguru.radius.models.FacilityExclusion =
    com.danielwaiguru.radius.models.FacilityExclusion(id, facilityId, optionsId)
internal fun FacilityEntity.toFacility(): com.danielwaiguru.radius.models.Facility =
    com.danielwaiguru.radius.models.Facility(
        facilityId = facilityId,
        name = name,
        options = options.map(FacilityEntity.OptionEntity::toOption)
    )

internal fun FacilityEntity.OptionEntity.toOption(): com.danielwaiguru.radius.models.Facility.SelectableOption =
    com.danielwaiguru.radius.models.Facility.SelectableOption(icon, id, name)