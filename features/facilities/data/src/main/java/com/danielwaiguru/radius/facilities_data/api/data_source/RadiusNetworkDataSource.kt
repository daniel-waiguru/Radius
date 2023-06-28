package com.danielwaiguru.radius.facilities_data.api.data_source

import com.danielwaiguru.radius.facilities_data.api.FacilitiesApiService
import com.danielwaiguru.radius.facilities_data.models.data.responses.FacilitiesResponse
import retrofit2.Response
import javax.inject.Inject

interface RadiusNetworkDataSource {
    suspend fun getFacilities(): Response<FacilitiesResponse>
}

internal class RadiusNetworkDataSourceImpl @Inject constructor(
    private val apiService: FacilitiesApiService
): RadiusNetworkDataSource {
    override suspend fun getFacilities(): Response<FacilitiesResponse> = apiService.getFacilities()
}