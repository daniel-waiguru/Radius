package com.danielwaiguru.radius.facilities_presentation.models

import com.danielwaiguru.radius.models.Facility

data class FacilitiesUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val facilities: List<Facility> = emptyList()
)
