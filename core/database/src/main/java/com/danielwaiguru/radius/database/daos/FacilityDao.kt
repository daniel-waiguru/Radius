package com.danielwaiguru.radius.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityEntity.Companion.FACILITIES_TABLE_NAME
import com.danielwaiguru.radius.database.entities.FacilityWithExclusions
import kotlinx.coroutines.flow.Flow

@Dao
interface FacilityDao {
    @Upsert
    suspend fun upsertFacilities(facilities: List<FacilityEntity>)

    @Query("SELECT * FROM $FACILITIES_TABLE_NAME")
    fun getFacilities(): Flow<List<FacilityEntity>>

}