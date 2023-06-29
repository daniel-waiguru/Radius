package com.danielwaiguru.radius.facilities_data.repository

import android.util.Log
import com.danielwaiguru.radius.common.utils.Dispatcher
import com.danielwaiguru.radius.common.utils.RadiusDispatchers
import com.danielwaiguru.radius.database.data_source.RadiusLocalDataSource
import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import com.danielwaiguru.radius.facilities_data.mappers.toFacility
import com.danielwaiguru.radius.facilities_data.mappers.toFacilityExclusion
import com.danielwaiguru.radius.models.Facility
import com.danielwaiguru.radius.models.FacilityExclusion
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FacilitiesRepositoryImpl @Inject constructor(
    private val radiusLocalDataSource: RadiusLocalDataSource,
    @Dispatcher(RadiusDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): FacilitiesRepository {
    override fun getFacilities(): Flow<List<Facility>> =
        radiusLocalDataSource.getFacilities()
            .map { facilitiesWithExclusions ->
                facilitiesWithExclusions.map(FacilityEntity::toFacility)
            }.flowOn(ioDispatcher)

    override fun getFacilityExclusions(): Flow<FacilityExclusion> =
        radiusLocalDataSource.getFacilityExclusions()
            .map { exclusion ->
                exclusion.toFacilityExclusion()
            }.flowOn(ioDispatcher)

    override suspend fun getFacilityExclusion(): FacilityExclusion? =
        radiusLocalDataSource.getFacilityExclusion()?.toFacilityExclusion()
}