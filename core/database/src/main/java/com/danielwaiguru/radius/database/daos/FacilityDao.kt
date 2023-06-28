package com.danielwaiguru.radius.database.daos

import androidx.room.Dao
import androidx.room.Upsert
import com.danielwaiguru.radius.database.entities.FacilityEntity

@Dao
interface FacilityDao {
    @Upsert
    suspend fun upsertFacilities(facilities: List<FacilityEntity>)

}