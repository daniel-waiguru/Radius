package com.danielwaiguru.radius.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity.Companion.FACILITIES_EXCLUSION_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface FacilityExclusionDao {
    @Upsert
    suspend fun upsertExclusions(exclusions: List<FacilityExclusionEntity>)

    @Query("SELECT * FROM $FACILITIES_EXCLUSION_TABLE_NAME")
    fun getExclusions(): Flow<FacilityExclusionEntity>

    @Query("SELECT * FROM $FACILITIES_EXCLUSION_TABLE_NAME")
    suspend fun getFacilityExclusion(): FacilityExclusionEntity?
}