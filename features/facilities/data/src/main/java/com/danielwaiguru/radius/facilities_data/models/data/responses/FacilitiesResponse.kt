package com.danielwaiguru.radius.facilities_data.models.data.responses

import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacility
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacilityExclusion

data class FacilitiesResponse(
    val facilities: List<NetworkFacility>,
    val exclusions: List<List<NetworkFacilityExclusion>>
)
