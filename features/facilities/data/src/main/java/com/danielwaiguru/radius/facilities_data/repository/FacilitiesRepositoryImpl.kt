package com.danielwaiguru.radius.facilities_data.repository

import com.danielwaiguru.radius.facilities_data.api.FacilitiesApiService
import javax.inject.Inject

internal class FacilitiesRepositoryImpl @Inject constructor(
    apiService: FacilitiesApiService
): FacilitiesRepository {
    override suspend fun upsertFacilities() {
        TODO("Not yet implemented")
    }
}