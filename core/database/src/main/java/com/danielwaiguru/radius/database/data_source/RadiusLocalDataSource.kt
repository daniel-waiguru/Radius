package com.danielwaiguru.radius.database.data_source

import com.danielwaiguru.radius.database.daos.FacilityDao
import com.danielwaiguru.radius.database.daos.FacilityExclusionDao
import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import javax.inject.Inject

interface RadiusLocalDataSource {
    suspend fun upsertFacilities(vararg facilities: FacilityEntity)

    suspend fun upsertFacilitiesExclusion(vararg exclusions: FacilityExclusionEntity)
}

internal class RadiusLocalDataSourceImpl @Inject constructor(
    private val facilityDao: FacilityDao,
    private val facilityExclusionDao: FacilityExclusionDao
): RadiusLocalDataSource {
    override suspend fun upsertFacilities(vararg facilities: FacilityEntity) =
        facilityDao.upsertFacilities(*facilities)

    override suspend fun upsertFacilitiesExclusion(vararg exclusions: FacilityExclusionEntity) =
        facilityExclusionDao.upsertExclusions(*exclusions)
}