package com.danielwaiguru.radius.facilities_data.repository

import com.danielwaiguru.radius.models.Facility
import com.danielwaiguru.radius.models.FacilityExclusion
import kotlinx.coroutines.flow.Flow

interface FacilitiesRepository {
    fun getFacilities(): Flow<List<Facility>>

    fun getFacilityExclusions(): Flow<FacilityExclusion>

    suspend fun getFacilityExclusion(): FacilityExclusion?
}