package com.danielwaiguru.radius.models

data class FacilityExclusion(
    val id: Int,
    val exclusions: List<List<Exclusion>>
) {
    data class Exclusion(
        val facilityId: String,
        val optionsId: String,
    )
}
