package com.danielwaiguru.radius.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class FacilityWithExclusions(
    @Embedded val facilityEntity: FacilityEntity,
    @Relation(
        parentColumn = "facility_id",
        entityColumn = "facility_id"
    )
    val exclusions: List<FacilityExclusionEntity>
)
