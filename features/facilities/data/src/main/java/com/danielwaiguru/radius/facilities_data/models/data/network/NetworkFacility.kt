package com.danielwaiguru.radius.facilities_data.models.data.network

import com.google.gson.annotations.SerializedName

data class NetworkFacility(
    @SerializedName("facility_id")
    val facilityId: String,
    val name: String,
    val options: List<Option>
) {
    data class Option(
        val icon: String,
        val id: String,
        val name: String
    )
}