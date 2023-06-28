package com.danielwaiguru.radius.database.daos

import androidx.room.Dao
import androidx.room.Upsert
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity

@Dao
internal interface FacilityExclusionDao {
    @Upsert
    suspend fun upsertExclusions(vararg exclusions: FacilityExclusionEntity)
}