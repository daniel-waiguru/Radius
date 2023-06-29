package com.danielwaiguru.radius.database.data_source

import com.danielwaiguru.radius.database.daos.FacilityDao
import com.danielwaiguru.radius.database.daos.FacilityExclusionDao
import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import com.danielwaiguru.radius.database.entities.FacilityWithExclusions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RadiusLocalDataSource {
    suspend fun upsertFacilities(facilities: List<FacilityEntity>)

    suspend fun upsertFacilitiesExclusion(exclusions: List<FacilityExclusionEntity>)

    fun getFacilities(): Flow<List<FacilityEntity>>

    fun getFacilityExclusions(): Flow<FacilityExclusionEntity>

    suspend fun getFacilityExclusion(): FacilityExclusionEntity?
}

internal class RadiusLocalDataSourceImpl @Inject constructor(
    private val facilityDao: FacilityDao,
    private val facilityExclusionDao: FacilityExclusionDao
): RadiusLocalDataSource {
    override suspend fun upsertFacilities(facilities: List<FacilityEntity>) =
        facilityDao.upsertFacilities(facilities)

    override suspend fun upsertFacilitiesExclusion(exclusions: List<FacilityExclusionEntity>) =
        facilityExclusionDao.upsertExclusions(exclusions)

    override fun getFacilities(): Flow<List<FacilityEntity>> =
        facilityDao.getFacilities()

    override fun getFacilityExclusions(): Flow<FacilityExclusionEntity> =
        facilityExclusionDao.getExclusions()

    override suspend fun getFacilityExclusion(): FacilityExclusionEntity? =
        facilityExclusionDao.getFacilityExclusion()
}