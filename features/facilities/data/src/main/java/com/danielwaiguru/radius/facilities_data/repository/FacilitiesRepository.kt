package com.danielwaiguru.radius.facilities_data.repository

import retrofit2.Response

interface FacilitiesRepository {
    suspend fun upsertFacilities()
}