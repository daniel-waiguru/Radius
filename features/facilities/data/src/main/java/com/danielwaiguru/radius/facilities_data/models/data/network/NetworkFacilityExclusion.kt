package com.danielwaiguru.radius.facilities_data.models.data.network

import com.google.gson.annotations.SerializedName

data class NetworkFacilityExclusion(
    @SerializedName("facility_id")
    val facilityId: String,
    @SerializedName("options_id")
    val optionsId: String
)
