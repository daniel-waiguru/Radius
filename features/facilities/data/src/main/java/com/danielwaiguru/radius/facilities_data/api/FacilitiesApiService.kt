package com.danielwaiguru.radius.facilities_data.api

import com.danielwaiguru.radius.facilities_data.models.data.responses.FacilitiesResponse
import com.danielwaiguru.radius.network.utils.ApiConstants.FACILITIES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface FacilitiesApiService {
    @GET(FACILITIES_ENDPOINT)
    suspend fun getFacilities(): Response<FacilitiesResponse>
}